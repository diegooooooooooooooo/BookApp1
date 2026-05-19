package com.example.bookapp.ui.libros;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J$\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020\u0017H\u0016J\u001a\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u001b2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u0007H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\'"}, d2 = {"Lcom/example/bookapp/ui/libros/CatalogoLibrosFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/example/bookapp/databinding/FragmentCatalogoLibrosBinding;", "allLibrosList", "", "Lcom/example/bookapp/data/entities/LibroEntity;", "binding", "getBinding", "()Lcom/example/bookapp/databinding/FragmentCatalogoLibrosBinding;", "loginViewModel", "Lcom/example/bookapp/viewmodel/LoginViewModel;", "getLoginViewModel", "()Lcom/example/bookapp/viewmodel/LoginViewModel;", "loginViewModel$delegate", "Lkotlin/Lazy;", "viewModel", "Lcom/example/bookapp/viewmodel/BibliotecaViewModel;", "getViewModel", "()Lcom/example/bookapp/viewmodel/BibliotecaViewModel;", "viewModel$delegate", "applyFilters", "", "adapter", "Lcom/example/bookapp/ui/libros/LibroAdapter;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "showDeleteConfirmation", "libro", "app_debug"})
public final class CatalogoLibrosFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.example.bookapp.databinding.FragmentCatalogoLibrosBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy loginViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.bookapp.data.entities.LibroEntity> allLibrosList;
    
    public CatalogoLibrosFragment() {
        super();
    }
    
    private final com.example.bookapp.databinding.FragmentCatalogoLibrosBinding getBinding() {
        return null;
    }
    
    private final com.example.bookapp.viewmodel.BibliotecaViewModel getViewModel() {
        return null;
    }
    
    private final com.example.bookapp.viewmodel.LoginViewModel getLoginViewModel() {
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
    
    private final void applyFilters(com.example.bookapp.ui.libros.LibroAdapter adapter) {
    }
    
    private final void showDeleteConfirmation(com.example.bookapp.data.entities.LibroEntity libro) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}