# Jupyter Dockerfile para C++, Java 9 e Python

Jupyter Dockerfile para C++, Java 9 e Python refere-se a receita que permite criar uma imagem funcional contendo o software [Jupyter Notebook](https://jupyter.org/) com os *kernels* para C++, Java 9 e Python configurados.

Como container base utilizou-se a imagem **Minimal Jupyter Notebook Stack**, que é parte do projeto [Jupyter Docker Stacks](https://github.com/jupyter/docker-stacks).

##  Imagem

- Kernel utilizado para suporte à linguagem C++ chama-se  [xeus-cling](https://github.com/QuantStack/xeus-cling) .  
- Kernel utilizado para suporte à linguagem Java chama-se [IJava](https://github.com/SpencerPark/IJava).

- Para instalação do Java Develpment Kit (JDK) utilizou-se a ferramenta [SDKMan](https://sdkman.io). No processo de instalação, a versão mais recente da JDK será instalada.

- Acompanha o arquivo de receita (Dockerfile) dois outros arquivos de configuração:
- - **jupyter_notebook_config.py**: arquivo base de configuração da ferramenta Jupyter Notebook.
- - **kernel.json**: arquivo de configuração do kernel *IJava*. 

## Volume

O container disponibiliza um volume com a finalidade de permitir a edição de arquivos de notebooks a partir do sistema de arquivos do *host*. O diretório mapeado como volume no sistema de arquivos do container é `/home/jovyan/notebooks`.


# Build & Run

Construir a imagem requer apenas um comando. O processo de construção leva alguns minutos para concluir em função da quantidade de arquivos necessários para configurar o ambiente. 

Um exemplo de comando de construção seria:

`docker build -t custom/jupyter-notebook-cpp-java .`

Uma vez construída a imagem, a execução do container é feita por meio do comando `docker run`. Importante ressaltar que é necessário realizar *bind* da porta 8888 para uma porta TCP local e, ainda, mapear o volume para um diretório do *host*.

Um exemplo de comando de criação de container seria:

`docker run --name jncontainer -d  -p 8888:8888 custom/jupyter-notebook-cpp-java -v ~/notebooks:/home/jovyan/notebooks`