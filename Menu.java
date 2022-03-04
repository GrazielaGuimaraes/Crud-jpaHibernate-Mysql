package controller;

import model.ConexaoFactory;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.*;
import java.util.List;
import java.util.Scanner;


public class Menu {




    public static void main(String[] args) {


       menuOpcoes();


    }


    public static void menuOpcoes(){

        System.out.println("Selecione a opção desejada: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Cadastrar usuário");
        System.out.println("2 - Listar todos os usuários");
        System.out.println("3 - Editar dados do usuário");
        System.out.println("4 - Remover usuário");
        int opcao = scanner.nextInt();




        if(opcao == 1) {
            createUsuario();

        } else if (opcao == 2) {
            readUsuario();

        }else if(opcao == 3){
            updateUsuario();

        }else if(opcao == 4) {
            removeUsuario();
        }


    }

    public static void createUsuario(){

        Usuario u = new Usuario();
        Scanner scanner = new Scanner(System.in);

        System.out.println("************** CADASTRO DE USUÁRIO *************");

        System.out.println("Informe o login de acesso do usuário");
        u.setLogin(scanner.nextLine());

        System.out.println("Informe a senha de acesso do usuário");
        u.setSenha(scanner.nextLine());


        EntityManager manager = ConexaoFactory.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(u);
        transaction.commit();

        System.out.println("Cadastrado com sucesso!");

        //lista
        readUsuario();

        manager.close();
        ConexaoFactory.close();







    }

    public static  void readUsuario(){



        EntityManager manager = ConexaoFactory.getEntityManager();


        Query query = manager.createQuery("select u from Usuario u");
        List<Usuario> usuarios = query.getResultList();

        for(Usuario u: usuarios){
            System.out.println("/*********Dados do usuário*********/");
            System.out.println("Código do usuário: " + u.getId());
            System.out.println("Login de acesso: " + u.getLogin());
            System.out.println("Senha de acesso: " + u.getSenha());
            System.out.println("**********************************");
        }

        manager.close();
        ConexaoFactory.close();
    }



    public static void updateUsuario(){


        Scanner scanner = new Scanner(System.in);

        System.out.println("************** ALTERAR USUÁRIO *************");

        Usuario usuario = new Usuario();

        EntityManager manager = ConexaoFactory.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();


        System.out.println("Informe o novo login de acesso: ");
        usuario.setLogin(scanner.nextLine());

        System.out.println("Informe a nova senha de acesso: ");
        usuario.setSenha(scanner.nextLine());

        System.out.println("Informe o id do usuário: ");
        usuario.setId(scanner.nextLong());



        manager.merge(usuario);
        transaction.commit();

        System.out.println("Atualizado com sucesso!");

        //lista
        readUsuario();

        manager.close();
        ConexaoFactory.close();


    }

    public static void removeUsuario(){





        System.out.println("************** REMOVER USUÁRIO *************");


        EntityManager manager = ConexaoFactory.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();


        //passando o id 8L
        Usuario u = manager.find(Usuario.class,8L);

        manager.remove(u);
        transaction.commit();

        System.out.println("Removido com sucesso!");

        //lista
        readUsuario();

        manager.close();
        ConexaoFactory.close();

    }


}
