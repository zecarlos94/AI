Ordem de implementação sugerida:
1º - Agente Definidor     10/11/2016 - 12/11/2016
2º - Agente Setor         10/11/2016 - 12/11/2016
3º - Agente Analisador    12/11/2016 - 18/11/2016
4º - Agente Licitador     18/11/2016 - 02/12/2016
5º - Agente Procura       18/11/2016 - 02/12/2016
6º - Agente Vendedor      18/11/2016 - 02/12/2016

Distribuição aleatória

Agente Procura: Mário/Rui
1. Produz uma lista com todas as bolsas de valores(SE) existentes e que permitam a realização de investimentos. 
2. Alerta o Agente Setor da abertura e do fecho de uma ou mais bolsas. 

Agente Definidor: Zé
1. Define a área ou setor de investimentos para o Agente Setor (observação da evolução dos preços individuais das ações ou mais rentáveis) trabalhará.

Agente Setor: Zé/André
1. Pesquisa de todas as empresas disponíveis nesse setor e nas bolsas de valores abertas. 
2. Transmite ao Agente Analisador essa lista de possíveis investimentos.
3. Alerta o Agente Analisador do fecho de uma ou mais bolsas. 

Agente Analisador: Armando/André/Zé
1. Separa as empresas a investir das não investir [mais(vender)] e qual o valor monetário máximo que se poderá gastar por ação a adquirir. 
2. Transmite ao Agente Licitador e ao Agente Vendedor.
	3. Alerta o Agente Licitador e ao Agente Vendedor do fecho de uma ou mais bolsas.
						
Agente Licitador: Rui
1. Executa licitações, por forma a adquirir o máximo de ações que conseguir até exceder o valor monetário estipulado para cada uma das empresas a investir. 
						
Agente Vendedor: Armando
1. Vende o máximo de ações que conseguir de todas as empresas listadas como não viáveis.
