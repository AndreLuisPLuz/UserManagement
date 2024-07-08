// package com.javaProject.startup.project.impl.mock;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.javaProject.startup.project.model.UserData;
// import com.javaProject.startup.project.services.UserService;

// import jakarta.annotation.PostConstruct;

// import com.javaProject.startup.project.model.Department;

// @Service
// // Implementação de um serviço fictício de usuário
// public class MockUserService implements UserService {

//     private List<UserData> users = new ArrayList<>(); // Lista para armazenar os usuários

//     // @Autowired
//     // MockDepartmentService mockDepartmentService;

//     // Construtor que inicializa alguns usuários fictícios com base nos departamentos obtidos do MockDepartmentService
//     @PostConstruct
//     public void init()
//     {
//         List<Department> departments = mockDepartmentService.getAll();

//         // Criação e inicialização de três usuários fictícios com diferentes papéis e departamentos
//         var user1 = new UserData();
//         user1.setId(1L);
//         user1.setUsername("trevisharp");
//         user1.setPassword("123456");
//         user1.setRole(1);
//         user1.setDepartment(departments.get(0));
//         users.add(user1);

//         var user2 = new UserData();
//         user2.setId(2L);
//         user2.setUsername("isaJK");
//         user2.setPassword("222555");
//         user2.setRole(0);
//         user2.setDepartment(departments.get(1));
//         users.add(user2);

//         var user3 = new UserData();
//         user3.setId(3L);
//         user3.setUsername("loregbr");
//         user3.setPassword("555555");
//         user3.setRole(0);
//         user3.setDepartment(departments.get(2));
//         users.add(user3);
//     }
    
//     // Método da interface UserService para criar um novo usuário
//     @Override
//     public UserData create(String username, Department department, Integer role) {
//         var newUser = new UserData();
//         newUser.setId((long) (users.size() + 1));
//         newUser.setUsername(username);
//         newUser.setRole(role);
//         newUser.setDepartment(department);

//         users.add(newUser);

//         return newUser;
//     }

//     // Método da interface UserService para atualizar a senha de um usuário
//     @Override
//     public Boolean updatePassword(Long id, String newPassword) {
//         for (UserData user : users) {
//             if (user.getId().equals(id)) {
//                 user.setPassword(newPassword);
//                 return true;
//             }
//         }
//         return false;
//     }

//     // Método da interface UserService para obter um usuário pelo nome de usuário
//     @Override
//     public UserData get(String username) {
//         for (UserData user : users) {
//             if (user.getUsername().equals(username)) {
//                 return user;
//             }
//         }
//         return null;
//     }
// }