// package com.fatec.bd.tracefinder.restapi.dao;

// import java.util.List;

// import com.fatec.bd.tracefinder.restapi.model.Usuario;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.BeanPropertyRowMapper;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.transaction.annotation.Transactional;

// @Transactional
// public class DAOUsuario {
//     @Autowired
// 	JdbcTemplate jdbcTemplate;
    
//     //private ArrayList<Usuario> listaUsuario = new ArrayList<>();
    
//     public void usuarioInsert(Usuario user) throws Exception {
//         String sql = "INSERT INTO usuario (usr_nome, usr_login, usr_senha, usr_email) VALUES (?,?,?,?)";

//         jdbcTemplate.update(
//             sql,
//             user.getUsuarioNome(), 
//             user.getUsuarioLogin(), 
//             user.getUsuarioSenha(),
//             user.getUsuarioEmail()
//         );      
//     }
    
//     public void usuarioUpdate (Usuario user) throws Exception {
//         String sql = "UPDATE usuario SET usuario_nome = ?, usuario_login = ?, usuario_senha = ?, usuario_email = ? WHERE usuario_id = ?";

//         jdbcTemplate.update(
//             sql,
//             user.getUsuarioNome(), 
//             user.getUsuarioLogin(), 
//             user.getUsuarioSenha(),
//             user.getUsuarioEmail(),
//             user.getUsuarioId()
//         );
//     }
    
//     public void usuarioDelete (int idUser) throws Exception {
//         String sql = "DELETE FROM usuario WHERE usuario_id = ?";

//         jdbcTemplate.update(
//             sql,
//             idUser
//         );
//     }
    
//     public List<Usuario> selectUsuario() throws Exception {
//         String sql = "SELECT * FROM usuario";

//         List<Usuario> usuarios = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Usuario>(Usuario.class));

//         return usuarios;
//    }
// }
