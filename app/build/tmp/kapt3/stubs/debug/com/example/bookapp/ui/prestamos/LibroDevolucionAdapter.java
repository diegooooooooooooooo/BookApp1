package com.example.bookapp.ui.prestamos;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001d2\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u001d\u001eB\u001f\u0012\u0018\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\u0006\u0010\u0014\u001a\u00020\u0007J\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006J\u001c\u0010\u0016\u001a\u00020\u00072\n\u0010\u0017\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u000fH\u0016J\u001c\u0010\u0019\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000fH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/example/bookapp/ui/prestamos/LibroDevolucionAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/example/bookapp/data/entities/PrestamoConDetalles;", "Lcom/example/bookapp/ui/prestamos/LibroDevolucionAdapter$ViewHolder;", "onSelectionChanged", "Lkotlin/Function1;", "", "", "(Lkotlin/jvm/functions/Function1;)V", "MULTA_DIARIA", "", "dateFormat", "Ljava/text/SimpleDateFormat;", "selectedItems", "", "", "calcularMulta", "fechaEsperada", "", "fechaReal", "clearSelection", "getSelectedPrestamos", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DiffCallback", "ViewHolder", "app_debug"})
public final class LibroDevolucionAdapter extends androidx.recyclerview.widget.ListAdapter<com.example.bookapp.data.entities.PrestamoConDetalles, com.example.bookapp.ui.prestamos.LibroDevolucionAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<java.util.List<com.example.bookapp.data.entities.PrestamoConDetalles>, kotlin.Unit> onSelectionChanged = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.Integer> selectedItems = null;
    @org.jetbrains.annotations.NotNull()
    private final java.text.SimpleDateFormat dateFormat = null;
    private final double MULTA_DIARIA = 50.0;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.bookapp.ui.prestamos.LibroDevolucionAdapter.DiffCallback DiffCallback = null;
    
    public LibroDevolucionAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.example.bookapp.data.entities.PrestamoConDetalles>, kotlin.Unit> onSelectionChanged) {
        super(null);
    }
    
    private final double calcularMulta(long fechaEsperada, long fechaReal) {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.bookapp.data.entities.PrestamoConDetalles> getSelectedPrestamos() {
        return null;
    }
    
    public final void clearSelection() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.example.bookapp.ui.prestamos.LibroDevolucionAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.ui.prestamos.LibroDevolucionAdapter.ViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/example/bookapp/ui/prestamos/LibroDevolucionAdapter$DiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/example/bookapp/data/entities/PrestamoConDetalles;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    public static final class DiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.example.bookapp.data.entities.PrestamoConDetalles> {
        
        private DiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.example.bookapp.data.entities.PrestamoConDetalles oldItem, @org.jetbrains.annotations.NotNull()
        com.example.bookapp.data.entities.PrestamoConDetalles newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.example.bookapp.data.entities.PrestamoConDetalles oldItem, @org.jetbrains.annotations.NotNull()
        com.example.bookapp.data.entities.PrestamoConDetalles newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/example/bookapp/ui/prestamos/LibroDevolucionAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/bookapp/databinding/ItemLibroDevolucionBinding;", "(Lcom/example/bookapp/ui/prestamos/LibroDevolucionAdapter;Lcom/example/bookapp/databinding/ItemLibroDevolucionBinding;)V", "bind", "", "prestamo", "Lcom/example/bookapp/data/entities/PrestamoConDetalles;", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.example.bookapp.databinding.ItemLibroDevolucionBinding binding = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        com.example.bookapp.databinding.ItemLibroDevolucionBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.example.bookapp.data.entities.PrestamoConDetalles prestamo) {
        }
    }
}