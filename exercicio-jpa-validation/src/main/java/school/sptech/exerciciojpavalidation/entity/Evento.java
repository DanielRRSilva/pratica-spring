package school.sptech.exerciciojpavalidation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 5, max = 100)
    private String nome;

    @NotBlank
    @Size(min = 5, max = 150)
    private String local;

    @NotNull
    @FutureOrPresent
    private LocalDate dataEvento;

    private LocalDate dataPublicacao = LocalDate.now();
    private boolean gratuito;
    private boolean cancelado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank @Size(min = 5, max = 100) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @Size(min = 5, max = 100) String nome) {
        this.nome = nome;
    }

    public @NotBlank @Size(min = 5, max = 150) String getLocal() {
        return local;
    }

    public void setLocal(@NotBlank @Size(min = 5, max = 150) String local) {
        this.local = local;
    }

    public @NotNull @FutureOrPresent LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(@NotNull @FutureOrPresent LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public boolean isGratuito() {
        return gratuito;
    }

    public void setGratuito(boolean gratuito) {
        this.gratuito = gratuito;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public String getStatus() {
        if (this.cancelado) {
            return "CANCELADO";
        }else if (this.dataEvento.isBefore(LocalDate.now())) {
            return "FINALIZADO";
        }
        return "ABERTO";
    }
}
