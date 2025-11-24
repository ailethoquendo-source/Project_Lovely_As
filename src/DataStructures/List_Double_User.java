package DataStructures;

import Models.Admin;
import Models.Client;
import Models.Nodes.Node_User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class List_Double_User {

    private Node_User head;

    public List_Double_User() {
        this.head = null;
    }

    public Node_User getHead() {
        return head;
    }

    public void setHead(Node_User head) {
        this.head = head;
    }

    public void clear_list() {
        head = null;
    }

    public void alert(Alert.AlertType alertType, String tit, String mj) {
        Alert a = new Alert(alertType);
        a.setTitle(tit);
        a.setContentText(mj);
        a.showAndWait();
    }

    public Node_User search_email(String email) {
        if (head == null) {
            return null;
        } else {
            Node_User aux = getHead();
            while (aux != null) {
                if (aux.getUser().getEmail().equalsIgnoreCase(email)) {
                    return aux;
                } else {
                    aux = aux.getNext();
                }
            }
            return null;
        }
    }

    public ObservableList<Node_User> getUsers() {
        ObservableList<Node_User> todos = FXCollections.observableArrayList();
        if (head == null) {
            return todos;
        }

        Node_User actual = head;

        do {
            todos.add(actual);
            actual = actual.getNext();

        } while (actual != null && actual != head);

        return todos;
    }

    public Node_User getLast() {

        if (head == null) {
            return null;
        } else {
            Node_User aux = head;
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            return aux;
        }
    }

    public Node_User create_user(TextField usernameField, TextField useridentField, TextField useremailField, PasswordField passwordField, String roll) {

        Node_User search = search_email(useremailField.getText());

        try {

            if (search != null) {
                alert(Alert.AlertType.WARNING, "Importante..!", "Ya existe un usuario con este correo.");
                return null;
            } else {
                Node_User newUser = null;

                if (roll.equals("ADMIN")) {
                    Admin admin = new Admin(Integer.parseInt(useridentField.getText()), usernameField.getText(), useremailField.getText(), passwordField.getText());
                    newUser = new Node_User(admin);
                } else {
                    Client client = new Client(Integer.parseInt(useridentField.getText()), usernameField.getText(), useremailField.getText(), passwordField.getText());
                    newUser = new Node_User(client);
                }

                alert(Alert.AlertType.CONFIRMATION, "Dialogo de confirmación", "Registro realizado con exito...!\n"
                        + "Felicidades...! ya haces parte de nuestros usuario :)");
                usernameField.setText("");
                useridentField.setText("");
                useremailField.setText("");
                passwordField.setText("");
                return newUser;
            }

        } catch (NumberFormatException e) {
            return null;
        }

    }

    public void addUser(String name, int identification, String email, String password, String roll) {

        Node_User newUser = null;

        if (roll.equals("ADMIN")) {
            Admin admin = new Admin(identification, name, email, password);
            newUser = new Node_User(admin);
        } else {
            Client client = new Client(identification, name, email, password);
            newUser = new Node_User(client);
        }

        if (newUser != null) {
            if (head == null) {
                head = newUser;
            } else {
                Node_User ultimo = getLast();
                ultimo.setNext(newUser);
                newUser.setFormer(ultimo);
            }
        }
    }
    
    public void addUser(TextField usernameField, TextField useridentField, TextField useremailField, PasswordField passwordField, String roll) {

        Node_User aux = create_user(usernameField, useridentField, useremailField, passwordField, roll);

        if (aux != null) {
            if (getHead() == null) {
                setHead(aux);
            } else {
                aux.setNext(getHead());
                getHead().setFormer(aux);
                setHead(aux);
            }
        }
    }

    public void save_data_file_users() {

        String direccion = System.getProperty("user.dir") + "\\src\\Files\\Users";

        Path archivo = Paths.get(direccion);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo.toFile(), false))) {
            Node_User current = head;

            while (current != null) {

                writer.write((current.getUser() instanceof Admin ? "ADMIN" : "CLIENT") + ", ");
                writer.write(current.getUser().getName() + ", ");
                writer.write(current.getUser().getIdentification() + ", ");                
                writer.write(current.getUser().getEmail() + ", ");
                writer.write(current.getUser().getPassword());

                writer.newLine();

                current = current.getNext();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los datos en el archivo de usuarios: " + e.getMessage());
        }
    }

    public void load_data_from_file_users() {

        String direction = System.getProperty("user.dir") + "\\src\\Files\\Users";

        Path file = Paths.get(direction);

        try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {

            String line;

            clear_list();

            while ((line = reader.readLine()) != null) {

                String[] atributes = line.split(", ");

                String roll = atributes[0];
                String name = atributes[1];
                int identification = Integer.parseInt(atributes[2]);
                String email = atributes[3];
                String password = atributes[4];

                addUser(name, identification, email, password, roll);
            }            
        } catch (IOException e) {
            System.out.println("Error al cargar los datos desde el archivo Users: " + e.getMessage());
        }
    }
}
