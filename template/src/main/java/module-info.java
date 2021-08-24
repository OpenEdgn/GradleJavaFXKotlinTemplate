module gradle.kotlin.template {
    requires kotlin.reflect;
    requires kotlin.stdlib;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    opens com.github.template;
    exports com.github.template;
}
