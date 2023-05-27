module game.fx.catsvsmice {
    exports game.fx.catsvsmice.model;
    opens game.fx.catsvsmice to com.google.gson;
    requires javafx.controls;
    requires com.google.gson;
    requires java.logging;
    exports game.fx.catsvsmice;
    exports game.fx.catsvsmice.model.objects;
}
