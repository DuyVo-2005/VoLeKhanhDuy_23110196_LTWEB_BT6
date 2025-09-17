package vn.khanhduy.controllers.admin.user;

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
import vn.khanhduy.dao.impl.RoleDaoImpl;
import vn.khanhduy.dao.impl.UserDaoImpl;
import vn.khanhduy.entities.Role;
import vn.khanhduy.entities.User;
import vn.khanhduy.utils.Constant;

@WebServlet(urlPatterns = { "/admin/user/home", "/admin/user/add", "/admin/user/edit", "/admin/user/delete" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDaoImpl userDaoImpl = new UserDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.startsWith("/admin/user/add")) {
			doGetAdd(req, resp);
		} else if (path.startsWith("/admin/user/edit")) {
			doGetEdit(req, resp);
		} else if (path.startsWith("/admin/user/delete")) {
			doGetDelete(req, resp);
		} else {
			doGetList(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.startsWith("/admin/user/add")) {
			doPostAdd(req, resp);
		} else if (path.startsWith("/admin/user/edit")) {
			doPostEdit(req, resp);
		}
	}

	protected void doGetList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String keyword = req.getParameter("keyword");
		List<User> userList;
		if(keyword != null && !keyword.trim().isEmpty()) {
			userList = userDaoImpl.searchByUsername(keyword);
		}
		else {
			userList = userDaoImpl.findAll();
		}
		req.setAttribute("userList", userList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/user/list-user.jsp");
		dispatcher.forward(req, resp);
	}

	protected void doGetAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RoleDaoImpl roleDaoImpl = new RoleDaoImpl();
		List<Role> roleList = roleDaoImpl.findAll();
		req.setAttribute("roleList", roleList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/user/add-user.jsp");
		dispatcher.forward(req, resp);
	}

	protected void doPostAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User();

		File uploadDir = new File(Constant.UPLOAD_DIR);
		if (!uploadDir.exists())
			uploadDir.mkdirs();

		try {
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");
			
			RoleDaoImpl roleDaoImpl = new RoleDaoImpl();
			Role role = roleDaoImpl.findById(Integer.parseInt(req.getParameter("roleId")));
			roleDaoImpl.update(role);//tranh loi detached
			user.setRole(role);

	        // Set các thuộc tính kháC
			user.setEmail(req.getParameter("email"));
			user.setFullname(req.getParameter("fullname"));
			user.setPassword(req.getParameter("password"));
			user.setPhone(req.getParameter("phone"));
			user.setUserName(req.getParameter("username"));

			String fileName = "";
			String newFileName = "";
			for (Part part : req.getParts()) {
				fileName = getFileName(part);
				if (fileName != null && !fileName.isEmpty()) {
					String uploadPath = Constant.UPLOAD_DIR + File.separator + "user";
					File uploadDirCategory = new File(uploadPath);
					if (!uploadDirCategory.exists())
						uploadDirCategory.mkdirs();

					// Tạo tên mới tránh trùng
					String ext = fileName.substring(fileName.lastIndexOf("."));
					newFileName = System.currentTimeMillis() + ext;

					part.write(uploadPath + File.separator + newFileName);
					user.setImageLink("user/" + newFileName);
				}
			}

			req.setAttribute("message", "File " + newFileName + " uploaded successfully!");
			userDaoImpl.insert(user);
			resp.sendRedirect(req.getContextPath() + "/admin/user/home");
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
		String id = req.getParameter("id");
		User user = userDaoImpl.findById(Integer.parseInt(id));
		req.setAttribute("user", user);

		RoleDaoImpl RoleDaoImpl = new RoleDaoImpl();
		List<Role> roleList = RoleDaoImpl.findAll();
		req.setAttribute("roleList", roleList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/user/edit-user.jsp");
		dispatcher.forward(req, resp);
	}

	protected void doPostEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");

			// Lấy id từ form
			int id = Integer.parseInt(req.getParameter("id"));

			// Lấy category cũ từ DB
			User user = userDaoImpl.findById(id);
			if (user == null) {
				resp.sendRedirect(req.getContextPath() + "/admin/user/home");
				return;
			}

			// Cập nhật giá trị mới
			RoleDaoImpl roleDaoImpl = new RoleDaoImpl();
			Role role = roleDaoImpl.findById(Integer.parseInt(req.getParameter("roleId")));
			roleDaoImpl.update(role);//tranh loi detached
			user.setRole(role);

			user.setEmail(req.getParameter("email"));
			user.setFullname(req.getParameter("fullname"));
			user.setPassword(req.getParameter("password"));
			user.setPhone(req.getParameter("phone"));
			user.setUserName(req.getParameter("username"));

			// Upload file mới nếu có
			String fileName = "";
			for (Part part : req.getParts()) {
				fileName = getFileName(part);
				if (fileName != null && !fileName.isEmpty()) {
					String uploadPath = Constant.UPLOAD_DIR + File.separator + "user";
					File uploadDirCategory = new File(uploadPath);
					if (!uploadDirCategory.exists())
						uploadDirCategory.mkdirs();

					part.write(uploadPath + File.separator + fileName);
					user.setImageLink("user/" + fileName);
				}
			}

			// Update DB
			userDaoImpl.update(user);

			resp.sendRedirect(req.getContextPath() + "/admin/user/home");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGetDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		userDaoImpl.delete(Integer.parseInt(id));
		resp.sendRedirect(req.getContextPath() + "/admin/user/home");
	}
}
