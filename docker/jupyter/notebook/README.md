# Jupyter Dockerfile para C++, Java 9 e Python

Jupyter Dockerfile para C++, Java 9 e Python refere-se à receita que permite criar uma imagem funcional contendo o software [Jupyter Notebook](https://jupyter.org/) contendo os kernels para C++, Java 9 e Python.

Como container base utilizou-se a imagem **Minimal Jupyter Notebook Stack**, que é parte do projeto [Jupyter Docker Stacks](https://github.com/jupyter/docker-stacks).

##Sobre a imagem

- Kernel utilizado para suporte à linguagem C++ chama-se  [xeus-cling](https://github.com/QuantStack/xeus-cling) .  
- Kernel utilizado para suporte à linguagem Java chama-se [IJava](https://github.com/SpencerPark/IJava)

- Para instalação do Java Develpment Kit (JDK) utilizou-se a ferramenta [SDKMan](https://sdkman.io). No processo de instalação, a versão mais recente de JDK é instalada.

- Acompanha o arquivo de receita (Dockerfile) dois outros arquivos de configuração:
- - **jupyter_notebook_config.py**: arquivo base de configuração da ferramenta Jupyter Notebook.
- - **kernel.json**: arquivo de configuração do kernel Java. 

## Volume

O container disponibiliza um volume com a finalidade de permitir a edição de arquivos de notebooks diretamente do sistema operacional do *host*. O diretório mapeado como volume no sistema de arquivos do container é `/home/jovyan/notebooks`.


# Build & Run

Construir a imagem requer apenas um comando. O processo requer alguns minutos em função da quantidade de arquivos que são necessários para configurar o ambiente. 

`docker build -t custom/jupyter-notebook-cpp-java .`

Uma vez construída a imagem, a execução do container é feita por meio do comando `docker run`. Importante ressaltar que é necessário realizar *bind* da porta 8888 para uma porta TCP local e, ainda, mapear o volume para um diretório do *host*.

`docker run --name jncontainer -d  -p 8888:8888 custom/jupyter-notebook-cpp-java -v ~/notebooks:/home/jovyan/notebooks`