package com.javaProject.startup.project.impl.mock;

import java.util.ArrayList;
import java.util.List;

import com.javaProject.startup.project.model.Department;
import com.javaProject.startup.project.services.DepartmentService;

// Implementação de um serviço de departamento fictício para fins de teste
public class MockDepartmentService implements DepartmentService {
    private List<Department> department = new ArrayList<>();

    // Construtor que inicializa a lista com alguns departamentos fictícios
    public MockDepartmentService() {
        var dep = new Department();
        dep.setId(1L);
        dep.setName("Controle de Qualidade");
        department.add(dep);

        dep = new Department();
        dep.setId(2L);
        dep.setName("Engenharia");
        department.add(dep);

        dep = new Department();
        dep.setId(3L);
        dep.setName("Logística");
        department.add(dep);

        dep = new Department();
        dep.setId(4L);
        dep.setName("Recursos Humanos");
        department.add(dep);
    }

    // Método da interface DepartmentService para obter todos os departamentos
    @Override
    public List<Department> getAll() {
        return department; // Retorna a lista de departamentos
    }
}