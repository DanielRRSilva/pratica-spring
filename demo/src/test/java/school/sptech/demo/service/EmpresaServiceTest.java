package school.sptech.demo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.demo.entity.Empresa;
import school.sptech.demo.exception.ConflitoException;
import school.sptech.demo.exception.EntidadeNaoEncontradaException;
import school.sptech.demo.repository.EmpresaRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste para EmpresaService")
@ExtendWith(MockitoExtension.class)
class EmpresaServiceTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaService empresaService;

    @Test
    @DisplayName("Dado que, não tenho nada no banco, retorna lista vazia")
    void buscarTodosListVazia() {

        Mockito.when(empresaRepository.findAll()).thenReturn(Collections.emptyList());
        List<Empresa> resultado = empresaService.buscarTodos();
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        Mockito.verify(empresaRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Dado que, tenho algo no banco, retorna lista com")
    void buscarTodosListaCheia() {
        List<Empresa> empresas = List.of(
                new Empresa(1,"Mec","Arcos Dourados","19.123.123/0001-20", LocalDate.of(1995,1 ,1)),
                new Empresa(2,"SPTECO","Educare","12.123.123/0001-20", LocalDate.of(2016,1 ,1))
        );

        Mockito.when(empresaRepository.findAll()).thenReturn(empresas);

        List<Empresa> resultado = empresaService.buscarTodos();

        assertNotNull(resultado);
        assertEquals(empresas.size(), resultado.size());
        for (int i = 0; i < resultado.size(); i++) {
            assertEquals(empresas.get(i).getId(), resultado.get(i).getId());
            assertEquals(empresas.get(i).getNomeFantasia(), resultado.get(i).getNomeFantasia());
            assertEquals(empresas.get(i).getRazaoSocial(), resultado.get(i).getRazaoSocial());
            assertEquals(empresas.get(i).getCnpj(), resultado.get(i).getCnpj());
            assertEquals(empresas.get(i).getDataFundacao(), resultado.get(i).getDataFundacao());
        }
        Mockito.verify(empresaRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Dado que, tenho uma empresa pelo id, retorne corretamento")
    void buscaPorIdCorreto() {
        Empresa empresaEsperada = new Empresa(1,"Mec","Arcos Dourados","19.123.123/0001-20", LocalDate.of(1995,1 ,1));
        Mockito.when(empresaRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(empresaEsperada));

        Empresa resultado = empresaService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(empresaEsperada.getId(), resultado.getId());
        Mockito.verify(empresaRepository, Mockito.times(1)).findById(1);
        Mockito.verify(empresaRepository, Mockito.never()).findAll();
    }

    @Test
    @DisplayName("Dado que, o id não existe, retorna exception")
    void buscaPorIdIncorreto() {
        Mockito.when(empresaRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

       EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> empresaService.buscarPorId(1));
       assertEquals("empresa", exception.getMessage());
    }

    @Test
    @DisplayName("Dado que, a empresa existe por CNPJ, deve retornar conflito")
    void criarEmpresaConflito() {
        Empresa empresaParaSalvar = new Empresa(1,"Mec","Arcos Dourados","19.123.123/0001-20", LocalDate.of(1995,1 ,1));

        Mockito.when(empresaRepository.existsByCnpj(empresaParaSalvar.getCnpj())).thenReturn(true);


        assertThrows(ConflitoException.class, ()-> empresaService.salvar(empresaParaSalvar));
        Mockito.verify(empresaRepository, Mockito.times(1)).existsByCnpj(empresaParaSalvar.getCnpj());
        Mockito.verify(empresaRepository, Mockito.never()).save(empresaParaSalvar);
    }

    @Test
    @DisplayName("Dado que, não existe cnpj, cadastra a empresa")
    void criarEmpresaSucesso() {
        Empresa empresaParaSalvar = new Empresa(1,"Mec","Arcos Dourados","19.123.123/0001-20", LocalDate.of(1995,1 ,1));

        Mockito.when(empresaRepository.existsByCnpj(empresaParaSalvar.getCnpj())).thenReturn(false);
        Mockito.when(empresaRepository.save(empresaParaSalvar)).thenReturn(empresaParaSalvar);

        Empresa empresaRetorno = empresaService.salvar(empresaParaSalvar);

        assertNotNull(empresaRetorno);
        assertEquals(empresaParaSalvar.getId(), empresaRetorno.getId());

        Mockito.verify(empresaRepository, Mockito.times(1)).existsByCnpj(empresaParaSalvar.getCnpj());
        Mockito.verify(empresaRepository, Mockito.times(1)).save(empresaParaSalvar);
    }
}
