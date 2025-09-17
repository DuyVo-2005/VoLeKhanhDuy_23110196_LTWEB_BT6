package vn.khanhduy.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "select c from User c")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int userId;
	
	@Column(columnDefinition = "varchar(200)", nullable = false)
	String userName;
	
	@Column(columnDefinition = "varchar(200)", nullable = false)
	String password;
	
	@Column(columnDefinition = "nvarchar(200)", nullable = false)
	String fullname;
	
	@Column(columnDefinition = "varchar(200)", nullable = false)
	String email;
	
	@Column(columnDefinition = "nvarchar(20)")
	String phone;
	
	@Column(columnDefinition = "nvarchar(max)")
	String imageLink;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Video> videos = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	List<Category> categories = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "roleId", unique = false)
	@JsonBackReference
	Role role;
	
}
