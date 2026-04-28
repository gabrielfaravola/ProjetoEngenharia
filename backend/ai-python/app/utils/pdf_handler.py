import os
import fitz
from pathlib import Path
from app.core.config import settings

#Buscar no Bucket- Definir um dos arquivos para teste
#Conectar ao Bucket
#Chamar API para salvar
DIRETORIO_PDFS = "eleicoesystem-bucket"
NOME_DO_ARQUIVO = "Eleicoes/2022/Presidente/PlanoGoverno_Jair_Bolsonaro.pdf"

# Ve se tem um bucket com o link recebido
# pega o conteudo e faz o processo de caracter
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