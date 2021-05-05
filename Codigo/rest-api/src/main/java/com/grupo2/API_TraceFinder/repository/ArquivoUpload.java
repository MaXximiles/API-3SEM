package com.grupo2.API_TraceFinder.repository;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.grupo2.API_TraceFinder.classes.Arquivo;
import com.grupo2.API_TraceFinder.classes.Lep;
import com.grupo2.API_TraceFinder.controller.ArquivoController;


@Component
public class ArquivoUpload {

	private CodelistRepository codelistRepository;
	private LepRepository lepRepository;
	private DocumentoRepository documentoRepository;
	private ArquivoRepository arquivoRepository;
	
	public ArquivoUpload(CodelistRepository codelistRepository, LepRepository lepRepository, DocumentoRepository DocumentoRepository, 
															ArquivoRepository ArquivoRepository)
	{
		this.codelistRepository = codelistRepository;
		this.lepRepository = lepRepository;
		this.documentoRepository = DocumentoRepository;
		this.arquivoRepository = ArquivoRepository;
	}
	
	public void salvarArquivo(MultipartFile arq, Long id) throws IOException
	{	
		var doc = codelistRepository.getOne(id);
		Long CodelistId = doc.getCodelistid();
		Long DocumentoId = doc.getDocumentoid();
		String nome = doc.getCodelistnomebloco();
		String caminho = doc.getCodelistcaminho();
		String code = doc.getCodelistcodebloco();
		String CodelistSecao = doc.getCodelistsecao();
		String CodelistSubsecao = doc.getCodelistsubsecao();
		
		String pasta = caminho + "\\" + nome;
		if(CodelistSecao != "") {pasta = pasta+"\\"+CodelistSecao;}
		if(CodelistSubsecao != "") {pasta = pasta+"\\"+CodelistSubsecao;}
		
		// Pegando nome do documento
		var documento = documentoRepository.getOne(DocumentoId);
		String DocumentoNome = documento.getDocumentonome();
		String DocumentoPn = documento.getDocumentopn();
		String DocNome = DocumentoNome+"-"+DocumentoPn;
				
		String nomeArquivo = DocNome+"-"+CodelistSecao; // Criando nome do arquivo seguindo padrão do mockup (nome doc + secao + subsecao + num - bloco)
		if(CodelistSubsecao != "") {nomeArquivo = nomeArquivo+"-"+CodelistSubsecao;}
		nomeArquivo = nomeArquivo+"-"+nome;
		
		String arquivo = arq.getOriginalFilename();

		this.salvar(pasta, arq, nomeArquivo, CodelistId);

		// Pegando quantidade de paginas do arquivo
		File file = new File(pasta+"\\"+arquivo);
		PDDocument document = PDDocument.load(file);
		int numPag = document.getNumberOfPages();
		
		// Laço para inserção das paginas na LEP
		/*for(int i = 1; i <= numPag; i++)
		{
			String pag = Integer.toString(i);
			
			var lep1 = new Lep();
			lep1.setLepBloco(nome);
			lep1.setLepCode(code);
			lep1.setLepPagina(pag);
			lep1.setLepModificacao(null); // mudar quando inserir modificacao
			lep1.setLepRevisao(null); // mudar quando inserir revisao
			lep1.setArquivoId(id);
			lep1.setDocumentoid(DocumentoId);
			lepRepository.save(lep1);
		}*/
		
		//document.save(pasta+"\\"+nomeArquivo+".pdf");
        document.close();
        
        new File(pasta+"\\"+arquivo).renameTo(new File(pasta+"\\"+nomeArquivo+".pdf")); // Renomeando o arquivo com o nome padrão mockup

	}
	
	
	public void salvar(String diretorio, MultipartFile arquivo, String nomeArquivo, Long CodelistId) throws IOException 
	{
		
		Path diretorioPath = Paths.get(diretorio);
		Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());
							
		try  
		{
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());
		} 
		catch (IOException e) {	throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);	}
		
	}
	
	
}
