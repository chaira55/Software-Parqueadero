import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Usuario extends JFrame {
    private final ArrayList<UsuarioInfo> usuarios = new ArrayList<>();
    private JTextField idField, nombreField, apellidoField, telefonoField, correoField;
    private JTextArea usuariosTextArea;

    public Usuario() {
        setTitle("Registro de Usuarios");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        
        JPanel formularioPanel = new JPanel(new GridBagLayout());
        formularioPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        idField = new JTextField(20);
        nombreField = new JTextField(20);
        apellidoField = new JTextField(20);
        telefonoField = new JTextField(20);
        correoField = new JTextField(20);

        c.gridx = 0;
        c.gridy = 0;
        formularioPanel.add(new JLabel("ID:"), c);
        c.gridx = 1;
        formularioPanel.add(idField, c);
        c.gridx = 0;
        c.gridy = 1;
        formularioPanel.add(new JLabel("Nombre:"), c);
        c.gridx = 1;
        formularioPanel.add(nombreField, c);
        c.gridx = 0;
        c.gridy = 2;
        formularioPanel.add(new JLabel("Apellido:"), c);
        c.gridx = 1;
        formularioPanel.add(apellidoField, c);
        c.gridx = 0;
        c.gridy = 3;
        formularioPanel.add(new JLabel("Teléfono:"), c);
        c.gridx = 1;
        formularioPanel.add(telefonoField, c);
        c.gridx = 0;
        c.gridy = 4;
        formularioPanel.add(new JLabel("Correo:"), c);
        c.gridx = 1;
        formularioPanel.add(correoField, c);

        JButton registrarButton = new JButton("Registrar Usuario");

        c.gridx = 1;
        c.gridy = 5;
        c.anchor = GridBagConstraints.WEST;
        formularioPanel.add(registrarButton, c);

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                int telefono = Integer.parseInt(telefonoField.getText());
                String correo = correoField.getText();

                UsuarioInfo usuario = new UsuarioInfo(id, nombre, apellido, telefono, correo);
                usuarios.add(usuario);

                idField.setText("");
                nombreField.setText("");
                apellidoField.setText("");
                telefonoField.setText("");
                correoField.setText("");
                actualizarUsuariosTextArea();
            }
        });

       
        JPanel listaUsuariosPanel = new JPanel(new BorderLayout());
        listaUsuariosPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        usuariosTextArea = new JTextArea();
        usuariosTextArea.setEditable(false);
        listaUsuariosPanel.add(new JScrollPane(usuariosTextArea));

        mainPanel.add(formularioPanel, BorderLayout.WEST);
        mainPanel.add(listaUsuariosPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void actualizarUsuariosTextArea() {
        usuariosTextArea.setText("Usuarios Registrados:\n");
        for (UsuarioInfo usuario : usuarios) {
            usuariosTextArea.append(usuario.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Usuario ventana = new Usuario();
                ventana.setVisible(true);
            }
        });
    }
}

class UsuarioInfo {
    private int id;
    private String nombre;
    private String apellido;
    private int telefono;
    private String correo;

    public UsuarioInfo(int id, String nombre, String apellido, int telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nNombre: " + nombre + "\nApellido: " + apellido + "\nTeléfono: " + telefono + "\nCorreo: " + correo;
    }
}
