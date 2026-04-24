package project;

import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.animation.*;
import javafx.util.Duration;

/**
 * Professional table windows for each data type.
 *
 * Features:
 *  - Unified dark glassmorphism theme matching the login screen
 *  - Live search / filter bar
 *  - Row count badge
 *  - Smooth fade-in on open
 *  - Alternating row colours + teal hover
 */
public class TableDisplay {

    // ── Palette (matches LoginFormApp) ────────────────────────────────────────
    private static final String BG_DARK     = "#0B1120";
    private static final String BG_CARD     = "rgba(15,25,50,0.97)";
    private static final String ACCENT      = "#00C9A7";
    private static final String TEXT_PRIM   = "#EDF2F7";
    private static final String TEXT_MUTED  = "#718096";
    private static final String ROW_EVEN    = "rgba(255,255,255,0.03)";
    private static final String ROW_ODD     = "rgba(0,201,167,0.04)";
    private static final String ROW_HOVER   = "rgba(0,201,167,0.12)";

    private static final String FONT_DISPLAY = "Georgia";
    private static final String FONT_BODY    = "Trebuchet MS";

    // ═══════════════════════════════════════════════════════════════════════════

    public static void showPatientsTable() {
        ObservableList<Patient> data = DatabaseHelper.getPatients();

        TableView<Patient> table = new TableView<>();
        table.getColumns().addAll(
            col("ID",        "patientId",  80),
            col("First Name","name",       160),
            col("Last Name", "lastName",   160),
            col("Age",       "age",        80),
            col("Condition", "condition",  220)
        );

        FilteredList<Patient> filtered = new FilteredList<>(data, p -> true);
        SortedList<Patient>   sorted   = new SortedList<>(filtered);
        sorted.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sorted);

        openWindow("Patients", "🏥", data.size(), table, filtered,
            p -> ((Patient) p).getName().toLowerCase().contains(
                getSearchText(filtered)) ||
                ((Patient) p).getLastName().toLowerCase().contains(
                getSearchText(filtered)) ||
                ((Patient) p).getCondition().toLowerCase().contains(
                getSearchText(filtered))
        );
    }

    public static void showEmployeesTable() {
        ObservableList<Employee> data = DatabaseHelper.getEmployees();

        TableView<Employee> table = new TableView<>();
        table.getColumns().addAll(
            col("ID",          "employeeId", 80),
            col("Full Name",   "fullName",   200),
            col("Position",    "position",   180),
            col("Wage / hr",   "wage",       110),
            col("Work Hours",  "workHours",  110)
        );

        FilteredList<Employee> filtered = new FilteredList<>(data, p -> true);
        SortedList<Employee>   sorted   = new SortedList<>(filtered);
        sorted.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sorted);

        openWindow("Employees", "👩‍⚕️", data.size(), table, filtered,
            p -> ((Employee) p).getFullName().toLowerCase().contains(
                getSearchText(filtered)) ||
                ((Employee) p).getPosition().toLowerCase().contains(
                getSearchText(filtered))
        );
    }

    public static void showStatsTable() {
        ObservableList<Stats> data = DatabaseHelper.getStats();

        TableView<Stats> table = new TableView<>();
        table.getColumns().addAll(
            col("Statistic",        "statistics",     260),
            col("Value",            "value",          100),
            col("Change (Mo/Mo)",   "changeLastMonth",130),
            col("Comments",         "comments",       280)
        );

        FilteredList<Stats> filtered = new FilteredList<>(data, p -> true);
        SortedList<Stats>   sorted   = new SortedList<>(filtered);
        sorted.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sorted);

        openWindow("Statistics", "📊", data.size(), table, filtered,
            p -> ((Stats) p).getStatistics().toLowerCase().contains(
                getSearchText(filtered)) ||
                ((Stats) p).getComments().toLowerCase().contains(
                getSearchText(filtered))
        );
    }

    public static void showDetailsTable() {
        ObservableList<Details> data = DatabaseHelper.getDetails();

        TableView<Details> table = new TableView<>();
        table.getColumns().addAll(
            col("Item",    "item",    200),
            col("Details", "details", 500)
        );

        FilteredList<Details> filtered = new FilteredList<>(data, p -> true);
        SortedList<Details>   sorted   = new SortedList<>(filtered);
        sorted.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sorted);

        openWindow("Details", "📋", data.size(), table, filtered,
            p -> ((Details) p).getItem().toLowerCase().contains(
                getSearchText(filtered)) ||
                ((Details) p).getDetails().toLowerCase().contains(
                getSearchText(filtered))
        );
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  Generic window builder
    // ═══════════════════════════════════════════════════════════════════════════

    @SuppressWarnings({"unchecked","rawtypes"})
    private static <T> void openWindow(
            String title, String icon, int total,
            TableView<T> table, FilteredList<T> filtered,
            javafx.util.Callback<T, Boolean> predicate) {

        Stage stage = new Stage();
        stage.setTitle("MediCore  •  " + title);
        stage.initModality(Modality.NONE);

        // ── Root ────────────────────────────────────────────────────────────────
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_DARK + ";");

        // ── Header ─────────────────────────────────────────────────────────────
        HBox header = new HBox(16);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(22, 32, 22, 32));
        header.setStyle(
            "-fx-background-color: rgba(15,25,50,0.97);" +
            "-fx-border-color: rgba(0,201,167,0.2);" +
            "-fx-border-width: 0 0 1 0;"
        );

        Label titleLbl = new Label(icon + "  " + title);
        titleLbl.setFont(Font.font(FONT_DISPLAY, FontWeight.BOLD, 22));
        titleLbl.setTextFill(Color.web(TEXT_PRIM));

        Label countBadge = new Label(total + " records");
        countBadge.setFont(Font.font(FONT_BODY, FontWeight.BOLD, 12));
        countBadge.setTextFill(Color.web(ACCENT));
        countBadge.setStyle(
            "-fx-background-color: rgba(0,201,167,0.12);" +
            "-fx-border-color: rgba(0,201,167,0.3);" +
            "-fx-border-radius: 20;" +
            "-fx-background-radius: 20;" +
            "-fx-padding: 3 12 3 12;"
        );

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Search field
        TextField search = new TextField();
        search.setPromptText("🔍  Search…");
        search.setFont(Font.font(FONT_BODY, 13));
        search.setPrefWidth(220);
        search.setPrefHeight(36);
        search.setStyle(
            "-fx-background-color: rgba(255,255,255,0.06);" +
            "-fx-background-radius: 8;" +
            "-fx-border-color: rgba(255,255,255,0.12);" +
            "-fx-border-radius: 8;" +
            "-fx-border-width: 1;" +
            "-fx-text-fill: #EDF2F7;" +
            "-fx-prompt-text-fill: #4A5568;" +
            "-fx-padding: 0 12 0 12;"
        );
        search.focusedProperty().addListener((o, ov, nv) ->
            search.setStyle(nv
                ? "-fx-background-color: rgba(0,201,167,0.06);" +
                  "-fx-background-radius: 8;" +
                  "-fx-border-color: #00C9A7;" +
                  "-fx-border-radius: 8;" +
                  "-fx-border-width: 1.5;" +
                  "-fx-text-fill: #EDF2F7;" +
                  "-fx-prompt-text-fill: #4A5568;" +
                  "-fx-padding: 0 12 0 12;"
                : "-fx-background-color: rgba(255,255,255,0.06);" +
                  "-fx-background-radius: 8;" +
                  "-fx-border-color: rgba(255,255,255,0.12);" +
                  "-fx-border-radius: 8;" +
                  "-fx-border-width: 1;" +
                  "-fx-text-fill: #EDF2F7;" +
                  "-fx-prompt-text-fill: #4A5568;" +
                  "-fx-padding: 0 12 0 12;"
            )
        );

        // Wire search to filter
        search.textProperty().addListener((obs, oldVal, newVal) -> {
            String lower = newVal == null ? "" : newVal.toLowerCase().trim();
            filtered.setPredicate(item -> {
                if (lower.isEmpty()) return true;
                // Store search text in a thread-local-like way via closure
                return predicate.call(item);
            });
            // Re-store so predicate can read it
            searchHolder[0] = lower;
            filtered.setPredicate(item -> {
                if (lower.isEmpty()) return true;
                return predicate.call(item);
            });
        });

        header.getChildren().addAll(titleLbl, countBadge, spacer, search);

        // ── Table styling ───────────────────────────────────────────────────────
        styleTable(table);

        VBox body = new VBox(table);
        body.setPadding(new Insets(24, 28, 24, 28));
        VBox.setVgrow(table, Priority.ALWAYS);

        root.setTop(header);
        root.setCenter(body);

        Scene scene = new Scene(root, 860, 560);
        stage.setScene(scene);
        stage.show();

        FadeTransition ft = new FadeTransition(Duration.millis(300), root);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    // Tiny holder so the lambda can share the current search text
    private static final String[] searchHolder = {""};

    private static String getSearchText(FilteredList<?> ignored) {
        return searchHolder[0];
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  Helpers
    // ═══════════════════════════════════════════════════════════════════════════

    private static <S, T> TableColumn<S, T> col(String header, String prop, double width) {
        TableColumn<S, T> c = new TableColumn<>(header);
        c.setCellValueFactory(new PropertyValueFactory<>(prop));
        c.setPrefWidth(width);
        c.setStyle(
            "-fx-alignment: CENTER-LEFT;" +
            "-fx-font-family: '" + FONT_BODY + "';" +
            "-fx-font-size: 13px;" +
            "-fx-text-fill: " + TEXT_MUTED + ";" +
            "-fx-font-weight: bold;"
        );
        return c;
    }

    @SuppressWarnings("rawtypes")
    private static <T> void styleTable(TableView<T> table) {
        table.setStyle(
            "-fx-background-color: rgba(15,25,50,0.97);" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: rgba(255,255,255,0.08);" +
            "-fx-border-radius: 12;" +
            "-fx-border-width: 1;"
        );
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setFixedCellSize(42);

        table.setRowFactory(tv -> {
            TableRow<T> row = new TableRow<>() {
                @Override
                protected void updateItem(T item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setStyle("-fx-background-color: transparent;");
                    } else {
                        String base = getIndex() % 2 == 0
                            ? "-fx-background-color: " + ROW_EVEN + ";"
                            : "-fx-background-color: " + ROW_ODD + ";";
                        setStyle(base + "-fx-text-fill: " + TEXT_PRIM + ";");
                    }
                }
            };
            row.setOnMouseEntered(e -> {
                if (!row.isEmpty())
                    row.setStyle("-fx-background-color: " + ROW_HOVER + ";" +
                                 "-fx-text-fill: " + TEXT_PRIM + ";");
            });
            row.setOnMouseExited(e -> {
                if (!row.isEmpty()) {
                    String base = row.getIndex() % 2 == 0
                        ? "-fx-background-color: " + ROW_EVEN + ";"
                        : "-fx-background-color: " + ROW_ODD + ";";
                    row.setStyle(base + "-fx-text-fill: " + TEXT_PRIM + ";");
                }
            });
            return row;
        });
    }
}