package school.sptech.demo.controller;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import school.sptech.demo.entity.Empresa;
import school.sptech.demo.service.EmpresaService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Teste unitário da EmpresaController")
class EmpresaControllerUnitTest {

    @Mock
    private EmpresaService empresaService;

    @InjectMocks
    private EmpresaController empresaController;

    @Test
    @DisplayName("Dado que, ao chamar buscarTodos, retorna uma lista cheia")
    void buscarTodosCorretamente() {
        List<Empresa> empresas = List.of(
            new Empresa(1,
                    "Mec",
                    "Arcos Dourados",
                    "19.123.123/0001-20",
                    LocalDate.of(1995,1 ,1))
        );

        Mockito.when(empresaService.buscarTodos()).thenReturn(empresas);

        ResponseEntity<List<Empresa>> resultado = empresaController.listar();

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertNotNull(resultado.getBody());
        assertFalse(resultado.getBody().isEmpty());
        assertEquals(empresas.get(0).getId(), resultado.getBody().get(0).getId());
    }

    @Test
    @DisplayName("Dado que, ao chamar buscarTodos, retorna uma lista vazia")
    void buscarTodosIncorretamente() {
        Mockito.when(empresaService.buscarTodos()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Empresa>> resultado = empresaController.listar();

        assertNull(resultado.getBody());
        assertEquals(HttpStatus.NO_CONTENT, resultado.getStatusCode());
    }

    @Test
    @DisplayName("Dado que, buscarPorId não tenha por id informado, lança exception")
    void porId() {
        Integer idInformado = 1;

        Mockito.when(empresaService.buscarPorId(idInformado)).thenThrow(new EntityNotFoundException("Empresa"));

        ResponseEntity<Empresa> resultado = empresaController.porId(idInformado);

        assertThrows(EntityNotFoundException.class, () -> empresaService.buscarPorId(idInformado));
        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatusCode());
    }

    @Test
    @DisplayName("Dado que, buscarPorId tenha empresa, retorna a empresa buscada")
    void porIdCorreto() {
        Integer idInformado = 1;
        Empresa empresa = new Empresa(1,"Mec","Arcos Dourados","19.123.123/0001-20", LocalDate.of(1995,1 ,1));

        Mockito.when(empresaService.buscarPorId(idInformado)).thenReturn(empresa);

        ResponseEntity<Empresa> resultado = empresaController.porId(idInformado);

        assertNotNull(resultado.getBody());
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
    }
}