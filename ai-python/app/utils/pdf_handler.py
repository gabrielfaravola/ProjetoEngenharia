import os
import fitz  # PyMuPDF
from pathlib import Path
from app.core.config import settings

#TODO - lógica para pegar cada arquivo 
DIRETORIO_PDFS = Path(settings.upload_dir)
NOME_DO_ARQUIVO = "13.pdf" 

# Inicializa o novo cliente oficial do Gemini

def PDFManager(nome_arquivo: str):
    caminho_completo = DIRETORIO_PDFS / nome_arquivo

    if not os.path.exists(caminho_completo):
        print(f"❌ Erro: O arquivo '{nome_arquivo}' não foi encontrado na pasta '{DIRETORIO_PDFS}'.")
        return None

    try:
        with fitz.open(caminho_completo) as doc:
            texto = ""
            for page in doc:
                texto += page.get_text()

            # Corta em 25.000 caracteres para não estourar os limites da camada gratuita
            return texto[:25000]
    except Exception as e:
        print(f"❌ Erro ao ler o PDF: {e}")
        return None