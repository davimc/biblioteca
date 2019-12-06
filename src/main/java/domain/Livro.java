package domain;

public class Livro {
    private String autor;
    private String titulo;
    private boolean isEmprestado;
    private boolean isReservado;

    public Livro(String autor, String titulo) {
        this.autor = autor;
        this.titulo = titulo;
        this.isEmprestado = false;
        this.isReservado = false;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isEmprestado() {
        return isEmprestado;
    }

    public void setEmprestado(boolean emprestado) {
        isEmprestado = emprestado;
    }

    public boolean isReservado() {
        return isReservado;
    }

    public void setReservado(boolean reservado) {
        isReservado = reservado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return autor.equals(livro.autor) &&
                titulo.equals(livro.titulo);
    }


    //others
    public void emprestar(){
        this.setEmprestado(true);
    }
    public void receber(){
        this.setEmprestado(false);
    }
    public void reservar(){
        this.setReservado(true);
    }
    public void cancelarReserva(){
        this.setReservado(false);
    }
}
