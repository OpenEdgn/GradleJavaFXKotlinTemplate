package com.github.template

import javafx.application.Application
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.stage.Stage

class Main : Application() {
    override fun start(primaryStage: Stage) {
        val root = VBox()
        root.alignment = Pos.CENTER
        val label = Label("Hello World.")
        val button = Button("CLICK")
        button.id = "button"
        VBox.setMargin(button, Insets(30.0, 0.0, 0.0, 0.0))
        button.setOnAction {
            button.text = "ACCEPT"
            println("ACCEPT")
        }
        root.children.addAll(label, button)
        label.font = Font.font(24.0)
        primaryStage.scene = Scene(root, 800.0, 600.0)
        primaryStage.title = "Main"
        primaryStage.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args)
}
