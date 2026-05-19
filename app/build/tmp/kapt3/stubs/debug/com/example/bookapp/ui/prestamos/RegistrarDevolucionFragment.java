package com.example.bookapp.ui.prestamos;

/**
 * Fragmento para registrar la devolución de uno o varios libros.
 * El bibliotecario selecciona un usuario y luego los libros a devolver.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0002J\u0018\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!H\u0002J\u0010\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020\u001bH\u0002J$\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020\u001bH\u0016J\u001a\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u00020(2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u00102\u001a\u00020\u001bH\u0002J\b\u00103\u001a\u00020\u001bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0014\u0010\r\u001a\u00020\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017\u00a8\u00064"}, d2 = {"Lcom/example/bookapp/ui/prestamos/RegistrarDevolucionFragment;", "Landroidx/fragment/app/Fragment;", "()V", "MULTA_DIARIA", "", "_binding", "Lcom/example/bookapp/databinding/FragmentRegistrarDevolucionBinding;", "args", "Lcom/example/bookapp/ui/prestamos/RegistrarDevolucionFragmentArgs;", "getArgs", "()Lcom/example/bookapp/ui/prestamos/RegistrarDevolucionFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "getBinding", "()Lcom/example/bookapp/databinding/FragmentRegistrarDevolucionBinding;", "libroAdapter", "Lcom/example/bookapp/ui/prestamos/LibroDevolucionAdapter;", "selectedSocio", "Lcom/example/bookapp/data/entities/SocioEntity;", "viewModel", "Lcom/example/bookapp/viewmodel/BibliotecaViewModel;", "getViewModel", "()Lcom/example/bookapp/viewmodel/BibliotecaViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "actualizarResumen", "", "selectedList", "", "Lcom/example/bookapp/data/entities/PrestamoConDetalles;", "calcularMulta", "fechaEsperada", "", "fechaReal", "cargarLibrosDeUsuario", "socioId", "", "confirmarDevolucionMasiva", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setupRecyclerView", "setupUserSelection", "app_debug"})
public final class RegistrarDevolucionFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.example.bookapp.databinding.FragmentRegistrarDevolucionBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.example.bookapp.ui.prestamos.LibroDevolucionAdapter libroAdapter;
    @org.jetbrains.annotations.Nullable()
    private com.example.bookapp.data.entities.SocioEntity selectedSocio;
    private final double MULTA_DIARIA = 50.0;
    
    public RegistrarDevolucionFragment() {
        super();
    }
    
    private final com.example.bookapp.databinding.FragmentRegistrarDevolucionBinding getBinding() {
        return null;
    }
    
    private final com.example.bookapp.ui.prestamos.RegistrarDevolucionFragmentArgs getArgs() {
        return null;
    }
    
    private final com.example.bookapp.viewmodel.BibliotecaViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void setupUserSelection() {
    }
    
    private final void cargarLibrosDeUsuario(int socioId) {
    }
    
    private final void actualizarResumen(java.util.List<com.example.bookapp.data.entities.PrestamoConDetalles> selectedList) {
    }
    
    private final double calcularMulta(long fechaEsperada, long fechaReal) {
        return 0.0;
    }
    
    private final void confirmarDevolucionMasiva() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}