package school.sptech.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.demo.entity.Empresa;
import school.sptech.demo.exception.ConflitoException;
import school.sptech.demo.exception.EntidadeNaoEncontradaException;
import school.sptech.demo.repository.EmpresaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public List<Empresa> buscarTodos() {
        return empresaRepository.findAll();
    }

    public Empresa buscarPorId(int i) {
        return empresaRepository.findById(i).orElseThrow(()-> new EntidadeNaoEncontradaException("empresa"));
    }

    public Empresa salvar(Empresa empresa) {
        if (empresaRepository.existsByCnpj(empresa.getCnpj())) throw new ConflitoException("CNPJ já existe.");

        return empresaRepository.save(empresa);
    }
}
