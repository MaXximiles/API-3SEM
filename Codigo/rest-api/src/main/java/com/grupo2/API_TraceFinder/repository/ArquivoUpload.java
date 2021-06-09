package com.grupo2.API_TraceFinder.repository;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.fdf.FDFPage;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.grupo2.API_TraceFinder.DBConexao;
import com.grupo2.API_TraceFinder.classes.Arquivo;
import com.grupo2.API_TraceFinder.classes.Lep;
import com.grupo2.API_TraceFinder.controller.ArquivoController;
import com.grupo2.API_TraceFinder.controller.dto.ArquivoRq;


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
	
	public void salvarArquivo(MultipartFile arq, Long id) throws Exception
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
        
		File arqAntigo  = new File(pasta+"\\"+arquivo);
		PDDocument pdf = PDDocument.load(arqAntigo);
		pdf.save(pasta+"\\"+nomeArquivo+".pdf");
		pdf.close();
		
		arqAntigo.delete();
				
		Long arqId = insertArquivo(null, id, nomeArquivo+".pdf");
	 	
	 	File file = new File(pasta+"\\"+nomeArquivo+".pdf");
		PDDocument document = PDDocument.load(file);
	 	int numPag = document.getNumberOfPages();
		
	 	String caminhoArquivo = pasta+"\\"+nomeArquivo+".pdf";
		String revisao;
		String modificacao = null;
		
		int contPage = 0;
		// Laço para inserção das paginas na LEP
		for(int i = 1; i <= numPag; i++)
		{	
	    	PDPage Pages = document.getPage(contPage);
            		
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);

            Rectangle2D area = new Rectangle2D.Float(0f, 570f, 400f, 60f);
            stripper.addRegion("rodape", area);
            stripper.extractRegions(Pages);            

            String pdfFileInText = stripper.getTextForRegion("rodape");
            
            int posicao = pdfFileInText.lastIndexOf("REVISION");
            
            if(posicao != -1){ revisao = pdfFileInText.substring(posicao, posicao+11); } 
            else{ revisao = "ORIGINAL"; }
            
				Long pag = (long) i;
							
				var lep1 = new Lep();
				lep1.setLepBloco(nome);
				lep1.setLepCode(code);
				lep1.setLepPagina(pag);
				lep1.setLepModificacao(modificacao); // mudar quando inserir modificacao
				lep1.setLepRevisao(revisao); // mudar quando inserir revisao
				lep1.setArquivoId(arqId);
				lep1.setDocumentoid(DocumentoId);
				lepRepository.save(lep1);
				
				contPage++;
				revisao = "";
            
		}
			
			document.close();
			
			updateArquivo(null, arqId, caminhoArquivo);
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
	
	public Long insertArquivo(@RequestBody ArquivoRq arquivo, Long id, String nome)
	{
		var arq = new Arquivo();
		arq.setArquivonome(nome);
		arq.setCodelistid(id);
		arquivoRepository.save(arq);
		
		return arq.getArquivoid();
	}
	
  public void updateArquivo(@RequestBody ArquivoRq arquivo, Long arqId, String arqCaminho ) throws Exception 
  {
	 Connection conn1 = null;
     ResultSet resultadoBanco1 = null;
     conn1 = DBConexao.abrirConexao();
     Statement stm1 = conn1.createStatement();
    
     String sql1 = "SELECT MAX(lep_revisao) FROM lep WHERE arquivo_id = "+arqId+";";
     resultadoBanco1 = stm1.executeQuery(sql1);
     
     String revisao = "";
     while(resultadoBanco1.next()){ revisao = resultadoBanco1.getString("MAX(lep_revisao)");}
	  
	  
	var arq = arquivoRepository.findById(arqId);
        
    if (arq.isPresent())
    {
      var arq2 = arq.get();
      arq2.setArquivorevisao(revisao);
      arq2.setArquivocaminho(arqCaminho);
      arquivoRepository.save(arq2);

    } else { throw new Exception("Documento não encontrado"); }
  }
	
	
}
