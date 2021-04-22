package spring.project.springboot.security;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.project.springboot.model.Usuario;
import spring.project.springboot.repository.UsuarioRepository;

@Service
@Transactional
public class ImplementacaoUserDatailService implements UserDetailsService, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findUserByLogin(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuário não foi encontrado");
			
		}
		
		return new User(usuario.getLogin(), 
						usuario.getPassword(), 
						usuario.isEnabled(), 
						true, 
						true, 
						true, usuario.getAuthorities());
	}

}
