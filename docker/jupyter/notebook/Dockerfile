ARG BASE_CONTAINER=jupyter/minimal-notebook
FROM $BASE_CONTAINER

LABEL maintainer="Diego Lusa"
LABEL maintainer.address="diego.antonio.lusa@gmail.com"

USER jovyan
WORKDIR /home/jovyan




#Install Java 9 with SDK
#Access https://sdkman.io/install for more details

USER root
RUN apt-get update &&  apt-get install  -yq --no-install-recommends curl zip
USER jovyan
RUN curl -s "https://get.sdkman.io" | bash
RUN /bin/bash -c "source /home/jovyan/.sdkman/bin/sdkman-init.sh && sdk install java"





#Install C++ kernel
RUN conda create -n cling
RUN echo 'source activate cling' | bash 
RUN conda install -y xeus-cling notebook -c QuantStack -c conda-forge


        

#Install IJava https://github.com/SpencerPark/IJava


WORKDIR /home/jovyan

RUN wget https://github.com/SpencerPark/IJava/releases/download/v1.2.0/ijava-1.2.0.zip -nv -O $HOME/ijava.zip
RUN unzip ijava.zip
RUN python3 install.py --sys-prefix


COPY jupyter_notebook_config.py /home/jovyan/.jupyter/jupyter_notebook_config.py
COPY kernel.json /opt/conda/share/jupyter/kernels/java/kernel.json 

VOLUME [ "/home/jovyan/notebooks" ]