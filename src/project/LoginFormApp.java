package project;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.Duration;

/**
 * Hospital Management System – main entry point.
 *
 * Design: refined dark medical theme with teal accents, glassmorphism card,
 * smooth fade/translate animations, and role-based dashboard routing.
 */
public class LoginFormApp extends Application {

    // ── Palette ───────────────────────────────────────────────────────────────
    private static final String BG_DARK      = "#0B1120";
    private static final String BG_CARD      = "rgba(15,25,50,0.85)";
    private static final String ACCENT       = "#00C9A7";
    private static final String ACCENT_DARK  = "#009E82";
    private static final String TEXT_PRIMARY = "#EDF2F7";
    private static final String TEXT_MUTED   = "#718096";
    private static final String ERROR_RED    = "#FC5C7D";
    private static final String FIELD_BG     = "rgba(255,255,255,0.06)";
    private static final String FIELD_BORDER = "rgba(255,255,255,0.12)";

    // ── Fonts (bundled with JavaFX) ────────────────────────────────────────────
    private static final String FONT_DISPLAY = "Georgia";
    private static final String FONT_BODY    = "Trebuchet MS";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("MediCore – Hospital Management System");
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
        primaryStage.centerOnScreen();

        showLoginScene(primaryStage);
        primaryStage.show();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  LOGIN SCENE
    // ═══════════════════════════════════════════════════════════════════════════

    private void showLoginScene(Stage stage) {

        // ── Background pane with gradient + decorative circles ─────────────────
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: " + BG_DARK + ";");

        // Decorative blobs
        root.getChildren().addAll(
            makeBlob(400, 400, "#00C9A7", 0.07, -250, -200),
            makeBlob(300, 300, "#6C63FF", 0.06,  300,  200),
            makeBlob(200, 200, "#FC5C7D", 0.05, -100,  250)
        );

        // ── Left panel – branding ──────────────────────────────────────────────
        VBox leftPanel = new VBox(24);
        leftPanel.setAlignment(Pos.CENTER_LEFT);
        leftPanel.setPadding(new Insets(60));
        leftPanel.setPrefWidth(420);

        Label logo = new Label("✚ MediCore");
        logo.setFont(Font.font(FONT_DISPLAY, FontWeight.BOLD, 36));
        logo.setTextFill(Color.web(ACCENT));

        Label tagline = new Label("Hospital Management\nSystem");
        tagline.setFont(Font.font(FONT_DISPLAY, FontWeight.NORMAL, 28));
        tagline.setTextFill(Color.web(TEXT_PRIMARY));

        Label sub = new Label("Secure, role-based access for\nhealthcare professionals.");
        sub.setFont(Font.font(FONT_BODY, 14));
        sub.setTextFill(Color.web(TEXT_MUTED));
        sub.setLineSpacing(4);

        // Feature badges
        VBox badges = new VBox(10);
        for (String feat : new String[]{
                "✔  BCrypt password security",
                "✔  Role-based access control",
                "✔  Live patient & employee data"}) {
            Label b = new Label(feat);
            b.setFont(Font.font(FONT_BODY, 13));
            b.setTextFill(Color.web(ACCENT));
        }

        leftPanel.getChildren().addAll(logo, tagline, sub, badges);

        // ── Right panel – login card ───────────────────────────────────────────
        VBox card = buildLoginCard(stage);

        HBox content = new HBox();
        content.setAlignment(Pos.CENTER);
        HBox.setHgrow(leftPanel, Priority.ALWAYS);
        HBox.setHgrow(card, Priority.ALWAYS);
        content.getChildren().addAll(leftPanel, card);

        root.getChildren().add(content);

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);

        // Fade in
        FadeTransition ft = new FadeTransition(Duration.millis(600), root);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    private VBox buildLoginCard(Stage stage) {

        // Card container
        VBox card = new VBox(20);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(50, 45, 50, 45));
        card.setMaxWidth(420);
        card.setStyle(
            "-fx-background-color: rgba(15,25,50,0.88);" +
            "-fx-background-radius: 20;" +
            "-fx-border-color: rgba(0,201,167,0.25);" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 20;"
        );
        card.setEffect(new DropShadow(40, Color.web("#000000", 0.5)));

        // Header
        Label title = new Label("Welcome back");
        title.setFont(Font.font(FONT_DISPLAY, FontWeight.BOLD, 26));
        title.setTextFill(Color.web(TEXT_PRIMARY));

        Label subtitle = new Label("Sign in to your account");
        subtitle.setFont(Font.font(FONT_BODY, 13));
        subtitle.setTextFill(Color.web(TEXT_MUTED));

        VBox header = new VBox(4, title, subtitle);
        header.setAlignment(Pos.CENTER);

        Separator sep = new Separator();
        sep.setStyle("-fx-background-color: rgba(255,255,255,0.08);");

        // Username field
        Label userLabel = fieldLabel("Username");
        TextField userField = styledTextField("Enter your username");

        // Password field
        Label passLabel = fieldLabel("Password");
        PasswordField passField = styledPasswordField("Enter your password");

        // Error label
        Label errorLabel = new Label();
        errorLabel.setFont(Font.font(FONT_BODY, FontWeight.BOLD, 13));
        errorLabel.setTextFill(Color.web(ERROR_RED));
        errorLabel.setVisible(false);
        errorLabel.setManaged(false);

        // Login button
        Button loginBtn = new Button("Sign In");
        loginBtn.setPrefWidth(Double.MAX_VALUE);
        loginBtn.setPrefHeight(46);
        loginBtn.setFont(Font.font(FONT_BODY, FontWeight.BOLD, 15));
        loginBtn.setStyle(accentButtonStyle());
        loginBtn.setCursor(Cursor.HAND);

        // Hover effect
        loginBtn.setOnMouseEntered(e ->
            loginBtn.setStyle(accentButtonHoverStyle()));
        loginBtn.setOnMouseExited(e ->
            loginBtn.setStyle(accentButtonStyle()));

        // Footer
        Label footer = new Label("MediCore v2.0  •  Secure Access Only");
        footer.setFont(Font.font(FONT_BODY, 11));
        footer.setTextFill(Color.web(TEXT_MUTED));

        // ── Login action ───────────────────────────────────────────────────────
        Runnable doLogin = () -> {
            String username = userField.getText().trim();
            String password = passField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                showError(errorLabel, "Please fill in all fields.");
                shake(card);
                return;
            }

            // Disable button during auth
            loginBtn.setDisable(true);
            loginBtn.setText("Signing in…");

            AuthResult result = DatabaseHelper.authenticateUser(username, password);

            if (result.isSuccess()) {
                FadeTransition out = new FadeTransition(Duration.millis(300),
                        stage.getScene().getRoot());
                out.setToValue(0);
                out.setOnFinished(ev ->
                    showDashboard(stage, result.getUsername(), result.getRole()));
                out.play();
            } else {
                loginBtn.setDisable(false);
                loginBtn.setText("Sign In");
                showError(errorLabel, "Invalid username or password.");
                shake(card);
                passField.clear();
            }
        };

        loginBtn.setOnAction(e -> doLogin.run());
        passField.setOnAction(e -> doLogin.run());

        VBox userBox = new VBox(6, userLabel, userField);
        VBox passBox = new VBox(6, passLabel, passField);

        card.getChildren().addAll(
            header, sep,
            userBox, passBox,
            errorLabel,
            loginBtn,
            footer
        );

        // Slide in from right
        card.setTranslateX(60);
        card.setOpacity(0);
        TranslateTransition tt = new TranslateTransition(Duration.millis(500), card);
        tt.setToX(0);
        FadeTransition ft = new FadeTransition(Duration.millis(500), card);
        ft.setToValue(1);
        ParallelTransition pt = new ParallelTransition(tt, ft);
        pt.setDelay(Duration.millis(150));
        pt.play();

        return card;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  DASHBOARD SCENE
    // ═══════════════════════════════════════════════════════════════════════════

    private void showDashboard(Stage stage, String username, UserRole role) {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_DARK + ";");

        // ── Top bar ────────────────────────────────────────────────────────────
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(18, 32, 18, 32));
        topBar.setSpacing(16);
        topBar.setStyle(
            "-fx-background-color: rgba(15,25,50,0.95);" +
            "-fx-border-color: rgba(0,201,167,0.2);" +
            "-fx-border-width: 0 0 1 0;"
        );

        Label logoTop = new Label("✚ MediCore");
        logoTop.setFont(Font.font(FONT_DISPLAY, FontWeight.BOLD, 22));
        logoTop.setTextFill(Color.web(ACCENT));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label roleTag = new Label(roleBadge(role));
        roleTag.setFont(Font.font(FONT_BODY, FontWeight.BOLD, 12));
        roleTag.setTextFill(Color.web(ACCENT));
        roleTag.setStyle(
            "-fx-background-color: rgba(0,201,167,0.12);" +
            "-fx-border-color: rgba(0,201,167,0.3);" +
            "-fx-border-radius: 20;" +
            "-fx-background-radius: 20;" +
            "-fx-padding: 4 12 4 12;"
        );

        Label userTag = new Label("👤  " + username);
        userTag.setFont(Font.font(FONT_BODY, 13));
        userTag.setTextFill(Color.web(TEXT_PRIMARY));

        Button logoutBtn = new Button("Log Out");
        logoutBtn.setFont(Font.font(FONT_BODY, FontWeight.BOLD, 12));
        logoutBtn.setCursor(Cursor.HAND);
        logoutBtn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-border-color: rgba(252,92,125,0.5);" +
            "-fx-border-radius: 8;" +
            "-fx-text-fill: #FC5C7D;" +
            "-fx-padding: 6 14 6 14;"
        );
        logoutBtn.setOnAction(e -> {
            FadeTransition ft = new FadeTransition(Duration.millis(300), root);
            ft.setToValue(0);
            ft.setOnFinished(ev -> showLoginScene(stage));
            ft.play();
        });

        topBar.getChildren().addAll(logoTop, spacer, roleTag, userTag, logoutBtn);

        // ── Main content ───────────────────────────────────────────────────────
        VBox center = new VBox(32);
        center.setAlignment(Pos.TOP_CENTER);
        center.setPadding(new Insets(48, 60, 48, 60));

        Label greeting = new Label("Good day, " + formatName(username) + ".");
        greeting.setFont(Font.font(FONT_DISPLAY, FontWeight.BOLD, 32));
        greeting.setTextFill(Color.web(TEXT_PRIMARY));

        Label hint = new Label("Select an action from your dashboard.");
        hint.setFont(Font.font(FONT_BODY, 15));
        hint.setTextFill(Color.web(TEXT_MUTED));

        FlowPane cards = new FlowPane(24, 24);
        cards.setAlignment(Pos.CENTER);

        // Build action cards based on role
        switch (role) {
            case NURSE:
                cards.getChildren().add(dashCard(
                    "🏥", "Patients", "View and manage patient records",
                    ACCENT, () -> TableDisplay.showPatientsTable()));
                break;

            case MANAGER:
                cards.getChildren().addAll(
                    dashCard("👩‍⚕️", "Employees", "View workforce & payroll data",
                        "#6C63FF", () -> TableDisplay.showEmployeesTable()),
                    dashCard("📊", "Statistics", "Monthly hospital performance stats",
                        "#FC5C7D", () -> TableDisplay.showStatsTable())
                );
                break;

            case VIEWER:
                cards.getChildren().add(dashCard(
                    "📋", "Details", "Read-only system details & info",
                    "#F6C90E", () -> TableDisplay.showDetailsTable()));
                break;
        }

        center.getChildren().addAll(greeting, hint, cards);

        root.setTop(topBar);
        root.setCenter(center);

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);

        FadeTransition ft = new FadeTransition(Duration.millis(400), root);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  HELPER – Dashboard card
    // ═══════════════════════════════════════════════════════════════════════════

    private VBox dashCard(String icon, String title, String desc,
                          String accentColor, Runnable action) {

        VBox card = new VBox(14);
        card.setAlignment(Pos.TOP_LEFT);
        card.setPadding(new Insets(28));
        card.setPrefWidth(240);
        card.setPrefHeight(180);
        card.setCursor(Cursor.HAND);
        card.setStyle(dashCardStyle(accentColor, false));

        Label iconLbl = new Label(icon);
        iconLbl.setFont(Font.font(36));

        Label titleLbl = new Label(title);
        titleLbl.setFont(Font.font(FONT_DISPLAY, FontWeight.BOLD, 20));
        titleLbl.setTextFill(Color.web(TEXT_PRIMARY));

        Label descLbl = new Label(desc);
        descLbl.setFont(Font.font(FONT_BODY, 13));
        descLbl.setTextFill(Color.web(TEXT_MUTED));
        descLbl.setWrapText(true);

        card.getChildren().addAll(iconLbl, titleLbl, descLbl);

        card.setOnMouseEntered(e -> card.setStyle(dashCardStyle(accentColor, true)));
        card.setOnMouseExited(e  -> card.setStyle(dashCardStyle(accentColor, false)));
        card.setOnMouseClicked(e -> action.run());

        // Slide up on load
        card.setTranslateY(30);
        card.setOpacity(0);
        TranslateTransition tt = new TranslateTransition(Duration.millis(400), card);
        tt.setToY(0);
        FadeTransition ft = new FadeTransition(Duration.millis(400), card);
        ft.setToValue(1);
        new ParallelTransition(tt, ft).play();

        return card;
    }

    private String dashCardStyle(String accent, boolean hover) {
        String bg = hover
            ? "rgba(0,201,167,0.08)"
            : "rgba(15,25,50,0.9)";
        String border = hover ? accent : "rgba(255,255,255,0.1)";
        return "-fx-background-color: " + bg + ";" +
               "-fx-background-radius: 16;" +
               "-fx-border-color: " + border + ";" +
               "-fx-border-width: 1;" +
               "-fx-border-radius: 16;" +
               "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 20, 0, 0, 4);";
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  HELPER – Decorative blob
    // ═══════════════════════════════════════════════════════════════════════════

    private Circle makeBlob(double w, double h, String color,
                            double opacity, double tx, double ty) {
        Circle c = new Circle(w / 2);
        c.setFill(Color.web(color, opacity));
        c.setTranslateX(tx);
        c.setTranslateY(ty);
        c.setEffect(new GaussianBlur(80));
        return c;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  HELPER – Form controls
    // ═══════════════════════════════════════════════════════════════════════════

    private Label fieldLabel(String text) {
        Label l = new Label(text);
        l.setFont(Font.font(FONT_BODY, FontWeight.BOLD, 13));
        l.setTextFill(Color.web(TEXT_MUTED));
        return l;
    }

    private TextField styledTextField(String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setFont(Font.font(FONT_BODY, 14));
        tf.setPrefHeight(44);
        tf.setStyle(fieldStyle());
        tf.focusedProperty().addListener((o, ov, nv) ->
            tf.setStyle(nv ? fieldFocusStyle() : fieldStyle()));
        return tf;
    }

    private PasswordField styledPasswordField(String prompt) {
        PasswordField pf = new PasswordField();
        pf.setPromptText(prompt);
        pf.setFont(Font.font(FONT_BODY, 14));
        pf.setPrefHeight(44);
        pf.setStyle(fieldStyle());
        pf.focusedProperty().addListener((o, ov, nv) ->
            pf.setStyle(nv ? fieldFocusStyle() : fieldStyle()));
        return pf;
    }

    private String fieldStyle() {
        return "-fx-background-color: rgba(255,255,255,0.06);" +
               "-fx-background-radius: 10;" +
               "-fx-border-color: rgba(255,255,255,0.12);" +
               "-fx-border-radius: 10;" +
               "-fx-border-width: 1;" +
               "-fx-text-fill: #EDF2F7;" +
               "-fx-prompt-text-fill: #4A5568;" +
               "-fx-padding: 0 14 0 14;";
    }

    private String fieldFocusStyle() {
        return "-fx-background-color: rgba(0,201,167,0.06);" +
               "-fx-background-radius: 10;" +
               "-fx-border-color: #00C9A7;" +
               "-fx-border-radius: 10;" +
               "-fx-border-width: 1.5;" +
               "-fx-text-fill: #EDF2F7;" +
               "-fx-prompt-text-fill: #4A5568;" +
               "-fx-padding: 0 14 0 14;";
    }

    private String accentButtonStyle() {
        return "-fx-background-color: #00C9A7;" +
               "-fx-text-fill: #0B1120;" +
               "-fx-background-radius: 10;" +
               "-fx-cursor: hand;";
    }

    private String accentButtonHoverStyle() {
        return "-fx-background-color: #009E82;" +
               "-fx-text-fill: #0B1120;" +
               "-fx-background-radius: 10;" +
               "-fx-cursor: hand;";
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  HELPER – Misc
    // ═══════════════════════════════════════════════════════════════════════════

    private void showError(Label label, String msg) {
        label.setText(msg);
        label.setVisible(true);
        label.setManaged(true);
    }

    /** Shake animation for bad login. */
    private void shake(Node node) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(60), node);
        tt.setFromX(0);
        tt.setByX(10);
        tt.setCycleCount(6);
        tt.setAutoReverse(true);
        tt.setOnFinished(e -> node.setTranslateX(0));
        tt.play();
    }

    private String roleBadge(UserRole role) {
        return switch (role) {
            case NURSE   -> "🩺  Nurse";
            case MANAGER -> "🏢  Manager";
            case VIEWER  -> "👁  Viewer";
        };
    }

    /** Converts snake_case usernames to Title Case for the greeting. */
    private String formatName(String username) {
        String[] parts = username.split("_");
        StringBuilder sb = new StringBuilder();
        for (String p : parts) {
            if (!p.isEmpty())
                sb.append(Character.toUpperCase(p.charAt(0)))
                  .append(p.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        launch(args);
    }
}