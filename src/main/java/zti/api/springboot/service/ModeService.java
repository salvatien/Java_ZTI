package zti.api.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zti.api.springboot.jpa.Mode;
import zti.api.springboot.jpa.ModeRepository;

@Service
public class ModeService {
	@Autowired
	private ModeRepository repository;
	
	
	public List<Mode> getModes() {
        return repository.findAll();
    }
	
	public Optional<Mode> getModeById(Long id) throws Exception {
		if (!repository.existsById(id)) {
		        throw new Exception("Not found");
		}
		return repository.findById(id);
	}
	
    public Mode createMode(Mode mode) {
        return repository.save(mode);
    }
    
}
