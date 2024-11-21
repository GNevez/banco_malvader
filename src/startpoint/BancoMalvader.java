package startpoint;

import controllers.UsuarioController;

import javax.swing.*;

public class BancoMalvader {
    private String nome;
    private static JFrame frame;

    public BancoMalvader(String nome) {
        this.nome = nome;
    }

    public void iniciarSistema() {
        // ...
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UsuarioController controller = new UsuarioController();
                JFrame mainView = controller.getMainView();
                mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainView.setSize(400, 600);
                mainView.setLocationRelativeTo(null);
                mainView.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao iniciar o sistema: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
