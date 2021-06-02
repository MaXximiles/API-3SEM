package com.grupo2.API_TraceFinder.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.API_TraceFinder.classes.Usuario;
import com.grupo2.API_TraceFinder.controller.dto.DocumentoRs;
import com.grupo2.API_TraceFinder.controller.dto.UsuarioRq;
import com.grupo2.API_TraceFinder.controller.dto.UsuarioRs;
import com.grupo2.API_TraceFinder.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

  private UsuarioRepository usuarioRepository = null;

  public UsuarioController(UsuarioRepository UsuarioRepository) {
    this.usuarioRepository = UsuarioRepository;
  }

  // SELECT de todos//
  @GetMapping("/")
  public List<UsuarioRs> selectAll() {
    var usuarios = usuarioRepository.findAll();
    return usuarios.stream().map((user) -> UsuarioRs.converter(user)).collect(Collectors.toList());
  }

  // SELECT por ID //
  @GetMapping("/{id}")
  public UsuarioRs selectID(@PathVariable("id") Long id) {
    var user = usuarioRepository.getOne(id);
    return UsuarioRs.converter(user);
  }

  // SELECT por Nome
  @GetMapping("/filtronome")
  public List<UsuarioRs> findUsuarioByusuarionome(@RequestParam("usuarionome") String usuarionome) {
    return this.usuarioRepository.findByusuarionomeContains(usuarionome).stream().map(UsuarioRs::converter)
        .collect(Collectors.toList());
  }

  // SELECT por login
  @GetMapping("/filtrologin")
  public List<UsuarioRs> findUsuarioByusuariologin(@RequestParam("usuariologin") String usuariologin) {
    return this.usuarioRepository.findByusuariologinContains(usuariologin).stream().map(UsuarioRs::converter)
        .collect(Collectors.toList());
  }

  // INSERT //
  @PostMapping("/")
  public void insertUsuario(@RequestBody UsuarioRq usuario) throws Exception {
    String senha = md5(usuario.getUsuariosenha());

    var user = new Usuario();

    user.setUsuarionome(usuario.getUsuarionome());
    user.setUsuariologin(usuario.getUsuariologin());
    user.setUsuariosenha(senha);
    user.setUsuarioemail(usuario.getUsuarioemail());
    user.setUsuarionivel(usuario.getUsuarionivel());

    usuarioRepository.save(user);

  }

  // UPDATE
  @PutMapping("/{id}")
  public void updateUsuario(@PathVariable Long id, @RequestBody UsuarioRq usuario) throws Exception {
    var user = usuarioRepository.findById(id);

    if (user.isPresent()) {
      String senha = md5(usuario.getUsuariosenha());

      var user2 = user.get();

      user2.setUsuarionome(usuario.getUsuarionome());
      user2.setUsuariologin(usuario.getUsuariologin());
      user2.setUsuariosenha(senha);
      user2.setUsuarioemail(usuario.getUsuarioemail());
      user2.setUsuarionivel(usuario.getUsuarionivel());
      usuarioRepository.save(user2);

    } else {
      throw new Exception("Documento não encontrado");
    }
  }

  // DELETE
  @DeleteMapping("/{id}")
  public void deleteUsuario(@PathVariable Long id) {
    usuarioRepository.deleteById(id);
  }

  // Função para criptografar senhas
  public static String md5(String valor) throws Exception {
    MessageDigest md = MessageDigest.getInstance("MD5");
    BigInteger hash = new BigInteger(1, md.digest(valor.getBytes()));
    String s = hash.toString(16);
    if (s.length() % 2 != 0) {
      s = "0" + s;
    }
    return s;
  }

  // Validação de Login e senha
  @GetMapping("/logar")
  public UsuarioRs Logar(@RequestParam("email") String email, @RequestParam("senha") String senha) throws Exception {
    var user = usuarioRepository.SelectUsuarioEmail(email);

    Long UserId = user.get(0).getUsuarioid();
    String UserSenha = user.get(0).getUsuariosenha();
    String UserEmail = user.get(0).getUsuarioemail();
    String UserNome = user.get(0).getUsuarionome();
    String UserNivel = user.get(0).getUsuarionivel();
    String UserLogin = user.get(0).getUsuariologin();

    if (user.isEmpty() == false) 
    {
      String SenhaCripto = md5(senha);

		  if (!senha.equals(SenhaCripto)) 
		  {
		    System.out.println("Login realizado com sucesso");
		    
		    /*String Json = "{\"UserID\":\'"+UserId+"\',"
		    		+ "\"UserSenha\":\'"+UserSenha+"',"
		    		+ "\"UserEmail\":\'"+UserEmail+"',"
		    		+ "\"UserNome\":\'"+UserNome+"',"
		    		+ "\"UserNivel\":\'"+UserNivel+"',"
		    		+ "\"UserLogin\":\'"+UserLogin+"'";
		    JSONObject jsonObject = new JSONObject(Json);*/
		    
		    var user1 = usuarioRepository.getOne(UserId);
		    return UsuarioRs.converter(user1);
		
		  } 
		  else 
		  {
		    System.out.println("Senha não confere");
		    return null;
		  }
    } 
    else 
    {
      return null;
    }

  }
}
