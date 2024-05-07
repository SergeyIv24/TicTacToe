module my.project.TicTacToe {
    requires javafx.controls;
    requires javafx.fxml;
    exports my.project.TicTacToe.GUI;
    opens my.project.TicTacToe.GUI to javafx.fxml;
    exports my.project.TicTacToe.GUI.controllers;
    opens my.project.TicTacToe.GUI.controllers to javafx.fxml;
}