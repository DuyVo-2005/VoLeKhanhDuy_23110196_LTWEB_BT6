package vn.khanhduy.controllers.admin.video;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.khanhduy.dao.impl.UserDaoImpl;
import vn.khanhduy.dao.impl.VideoDaoImpl;
import vn.khanhduy.entities.User;
import vn.khanhduy.entities.Video;
import vn.khanhduy.utils.Constant;

@WebServlet(urlPatterns = { "/admin/video/home", "/admin/video/add", "/admin/video/edit", "/admin/video/delete" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
public class VideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	VideoDaoImpl videoDaoImpl = new VideoDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.startsWith("/admin/video/add")) {
			doGetAdd(req, resp);
		} else if (path.startsWith("/admin/video/edit")) {
			doGetEdit(req, resp);
		} else if (path.startsWith("/admin/video/delete")) {
			doGetDelete(req, resp);
		} else {
			doGetList(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.startsWith("/admin/video/add")) {
			doPostAdd(req, resp);
		} else if (path.startsWith("/admin/video/edit")) {
			doPostEdit(req, resp);
		}
	}

	protected void doGetList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String keyword = req.getParameter("keyword");
		List<Video> videoList;
		if (keyword != null && !keyword.trim().isEmpty()) {
			videoList = videoDaoImpl.searchByKeyword(keyword);
		} else {
			videoList = videoDaoImpl.findAll();// load tất cả nếu không search
		}
		req.setAttribute("videoList", videoList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/video/list-video.jsp");
		dispatcher.forward(req, resp);
	}

	protected void doGetAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		List<User> userList = userDaoImpl.findAll();
		req.setAttribute("userList", userList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/video/add-video.jsp");
		dispatcher.forward(req, resp);
	}

	protected void doPostAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Video video = new Video();

		File uploadDir = new File(Constant.UPLOAD_DIR);
		if (!uploadDir.exists())
			uploadDir.mkdirs();

		try {
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");

			String userIdStr = req.getParameter("userId");
			if (userIdStr == null || userIdStr.isEmpty()) {
				throw new ServletException("User chưa được chọn!");
			}

			int userId = Integer.parseInt(userIdStr);
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			User user = userDaoImpl.findById(userId);
			userDaoImpl.update(user);// tranh loi detached
			video.setUser(user);

			String fileName = "";
			String newFileName = "";
			for (Part part : req.getParts()) {
				fileName = getFileName(part);
				if (fileName != null && !fileName.isEmpty()) {
					String uploadPath = Constant.UPLOAD_DIR + File.separator + "video";
					File uploadDirVideo = new File(uploadPath);
					if (!uploadDirVideo.exists())
						uploadDirVideo.mkdirs();

					// Cắt chuỗi từ dấu "." đến hết chuỗi -> lấy phần mở rộng của file
					String ext = fileName.substring(fileName.lastIndexOf("."));
					// Tạo tên mới tránh trùng
					newFileName = System.currentTimeMillis() + ext;

					part.write(uploadPath + File.separator + newFileName);
					video.setVideoLink("video/" + newFileName);
				}
			}

			req.setAttribute("message", "File " + newFileName + " uploaded successfully!");
			videoDaoImpl.insert(video);
			resp.sendRedirect(req.getContextPath() + "/admin/video/home");
		} catch (FileNotFoundException fne) {
			req.setAttribute("message", "There was an error: " + fne.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getFileName(Part part) {
		return part.getSubmittedFileName();
	}

	protected void doGetEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String videoId = req.getParameter("id");
		if (videoId == null) {
			resp.sendRedirect(req.getContextPath() + "/admin/video/home");
			return;
		}

		int id = Integer.parseInt(videoId);
		Video video = videoDaoImpl.findById(id);
		if (video == null) {
			resp.sendRedirect(req.getContextPath() + "/admin/video/home");
			return;
		}

		UserDaoImpl userDaoImpl = new UserDaoImpl();
		List<User> userList = userDaoImpl.findAll();
		req.setAttribute("userList", userList);
		req.setAttribute("video", video);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/video/edit-video.jsp");
		dispatcher.forward(req, resp);
	}

	protected void doPostEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");

			// Lấy id từ form
			int id = Integer.parseInt(req.getParameter("id"));

			// Lấy video cũ từ DB
			Video video = videoDaoImpl.findById(id);
			if (video == null) {
				resp.sendRedirect(req.getContextPath() + "/admin/video/home");
				return;
			}

			// Cập nhật giá trị mới
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			User user = userDaoImpl.findById(Integer.parseInt(req.getParameter("userId")));
			userDaoImpl.update(user);// tranh loi detached
			video.setUser(user);

			// Upload file mới nếu có
			String fileName = "";
			for (Part part : req.getParts()) {
				fileName = getFileName(part);
				if (fileName != null && !fileName.isEmpty()) {
					String uploadPath = Constant.UPLOAD_DIR + File.separator + "video";
					File uploadDirVideo = new File(uploadPath);
					if (!uploadDirVideo.exists())
						uploadDirVideo.mkdirs();

					part.write(uploadPath + File.separator + fileName);
					video.setVideoLink("video/" + fileName);
				}
			}

			videoDaoImpl.update(video);

			resp.sendRedirect(req.getContextPath() + "/admin/video/home");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGetDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		videoDaoImpl.delete(Integer.parseInt(id));
		resp.sendRedirect(req.getContextPath() + "/admin/video/home");
	}
}
