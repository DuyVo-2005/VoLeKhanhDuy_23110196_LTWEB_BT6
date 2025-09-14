package vn.khanhduy.controllers.admin.category;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.khanhduy.entities.Category;
import vn.khanhduy.entities.User;
import vn.khanhduy.utils.Constant;
import vn.khanhduy.dao.impl.CategoryDaoImpl;;

@WebServlet(urlPatterns = { "/admin/category/home", "/admin/category/add", "/admin/category/edit", "/admin/category/delete" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private CategoryDaoImpl categoryDaoImpl;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * http://localhost:8080/MyWebApp/admin/add req.getContextPath() -> /MyWebApp
		 * 
		 * req.getServletPath() -> /admin/add
		 */
		String path = req.getServletPath();// hoặc req.getRequestURI()
		if (path.startsWith("/admin/category/add")) {
			doGetAdd(req, resp);
		} else if (path.startsWith("/admin/category/edit")) {
			doGetEdit(req, resp);
		} else if (path.startsWith("/admin/category/delete")) {
			doGetDelete(req, resp);
		} else {
			doGetList(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.startsWith("/admin/category/add")) {
			doPostAdd(req, resp);
		} else if (path.startsWith("/admin/category/edit")) {
			doPostEdit(req, resp);
		}
	}

	protected void doGetList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> cateList = categoryDaoImpl.findAll();
		req.setAttribute("cateList", cateList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/category/list-category.jsp");
		dispatcher.forward(req, resp);
	}

	protected void doGetAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/category/add-category.jsp");
		dispatcher.forward(req, resp);
	}

	protected void doPostAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Category category = new Category();

		File uploadDir = new File(Constant.UPLOAD_DIR);
		if (!uploadDir.exists())
			uploadDir.mkdirs();

		try {
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");

			// Lấy tên category từ form
			category.setCategoryName(req.getParameter("name"));

			// Lấy user từ session
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("account");

			category.setUser(user); // gán user cho category

			String fileName = "";
			String newFileName = "";
			for (Part part : req.getParts()) {
				fileName = getFileName(part);
				if (fileName != null && !fileName.isEmpty()) {
					String uploadPath = Constant.UPLOAD_DIR + File.separator + "category";
					File uploadDirCategory = new File(uploadPath);
					if (!uploadDirCategory.exists())
						uploadDirCategory.mkdirs();

					// Tạo tên mới tránh trùng
					String ext = fileName.substring(fileName.lastIndexOf("."));
					newFileName = System.currentTimeMillis() + ext;

					part.write(uploadPath + File.separator + newFileName);
					category.setImages("category/" + newFileName);
				}
			}

			req.setAttribute("message", "File " + newFileName + " uploaded successfully!");
			categoryDaoImpl.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/category/home");
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
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("USERMODEL");// user đang login
		// Users currentUser = (Users) SessionUtil.getInstance().getValue(req,
		// "USERMODEL");

		Category category = categoryDaoImpl.findById(Integer.parseInt(id));

		if (category != null && category.getUser().getUserId() == currentUser.getUserId()) {
			req.setAttribute("category", category);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/category/edit-category.jsp");
			dispatcher.forward(req, resp);
		} else {
			// Không có quyền chỉnh sửa
			resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền chỉnh sửa category này!");
		}
	}

	protected void doPostEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");

			// Lấy id từ form
			int id = Integer.parseInt(req.getParameter("id"));

			// Lấy category cũ từ DB
			Category category = categoryDaoImpl.findById(id);
			if (category == null) {
				resp.sendRedirect(req.getContextPath() + "/admin/category/home");
				return;
			}

			// Cập nhật tên mới
			category.setCategoryName(req.getParameter("name"));

			// Upload file mới nếu có
			String fileName = "";
			for (Part part : req.getParts()) {
				fileName = getFileName(part);
				if (fileName != null && !fileName.isEmpty()) {
					String uploadPath = Constant.UPLOAD_DIR + File.separator + "category";
					File uploadDirCategory = new File(uploadPath);
					if (!uploadDirCategory.exists())
						uploadDirCategory.mkdirs();

					part.write(uploadPath + File.separator + fileName);
					category.setImages("category/" + fileName);
				}
			}

			// Update DB
			categoryDaoImpl.update(category);

			resp.sendRedirect(req.getContextPath() + "/admin/category/home");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGetDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		HttpSession session = req.getSession();
		Category category = categoryDaoImpl.findById(Integer.parseInt(id));
		User currrentUser = (User) session.getAttribute("USERMODEL");

		if (id != null && category.getUser().getUserId() == currrentUser.getUserId()) {
			categoryDaoImpl.delete(Integer.parseInt(id));
			resp.sendRedirect(req.getContextPath() + "/admin/category/home");
		} else {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền xóa category này!");
		}
	}

}
