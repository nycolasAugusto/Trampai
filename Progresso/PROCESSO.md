# 📜 PROCESSO.md — Documentação, Histórico e Decisões do Projeto (Trampai)

Este documento detalha o processo de desenvolvimento do aplicativo **Trampai**, respondendo às diretrizes da especificação do trabalho com o histórico de decisões, mudanças e dificuldades.

---

## 🎬 Demonstração em Vídeo

Abaixo estão os vídeos demonstrando a evolução e as decisões do projeto:

* **Vídeo 1 - Versão 1 do App:** [Cole o link do seu vídeo aqui]
* **Vídeo 2 - Escolhas Feitas na Versão 2:** [Cole o link do seu vídeo aqui]

---

## 1. Como estava o projeto no Trabalho 1, e o que mudou para chegar até aqui?

| Critério | Trabalho 1 (Versão Anterior) | Trabalho 2 (Versão Atual) |
| :--- | :--- | :--- |
| **Dados** | Estático, mockado em variáveis fixas ou sem reatividade. | Totalmente reativo utilizando `mutableStateListOf` num `ViewModel`. |
| **Modelos** | 1 tipo de item genérico. | **2 Data Classes:** `Servico` e `Proposta`, com relacionamento. |
| **Listas** | Listagens estáticas. | Duas telas de lista com `LazyColumn` + suporte a exclusão. |
| **Inputs** | Sem campos funcionais. | Formulários interativos para criar serviços e enviar propostas. |
| **Navegação**| Troca simples de tela. | **NavHost** completo com Bottom Navigation e passagem de argumentos. |

---

## 2. Por que essas telas novas — o que cada uma faz e por que o trio escolheu elas?

Para refletir um aplicativo real de conexão entre clientes e prestadores, expandimos as telas e criamos um fluxo completo. Abaixo estão as telas escolhidas e nossos esboços:

### Esboço Inicial das Telas
![Esboço Telas](materiaisProgesso/EsboçoTelas.png)

### Telas do Aplicativo:

**1. Aba Início (Tela Home)**
Exibe a lista reativa de serviços disponíveis. Foi escolhida para ser o ponto central onde o usuário visualiza rapidamente as demandas ativas.
![Tela Home](materiaisProgesso/telaHome2.0.png)

**2. Aba Buscar**
Permite filtrar ou procurar serviços específicos, essencial para a usabilidade de um marketplace de serviços.
![Tela Buscar](materiaisProgesso/TelaBuscarServico2.0.png)

**3. Aba Criar Serviço**
O formulário para cadastrar novos serviços. Cumpre a exigência de adicionar itens dinamicamente pela interface.
![Tela Criar Serviço](materiaisProgesso/TelaCriarServico2.0.png)

**4. Aba Urgente (Job Urgente)**
Aba dedicada para destacar chamados emergenciais.
![Tela Job Urgente](materiaisProgesso/TelaJOburgente2.0.png)

**5. Aba Meu Perfil**
Centraliza os dados do usuário e permite a gestão do aplicativo.
![Tela Meu Perfil](materiaisProgesso/telaMeuPerfil2.0.png)

**6. Telas de Gestão de Propostas**
Lista os orçamentos/propostas enviadas para os serviços, permitindo gerenciar e remover propostas cadastradas.
![Tela Propostas 1](materiaisProgesso/telaPropostas2.0-1.png)
![Tela Propostas 2](materiaisProgesso/telaPropostas2.0-2.png)

---

## 3. Que decisão de configuração/organização do código o trio tomou, e por quê?

* **Organização das Rotas:** Criamos uma estrutura de navegação centralizada para evitar erros com strings soltas.
* **NavHost Aninhado:** Para resolver o layout da barra de navegação inferior (BottomBar), separamos o NavHost. As abas principais (Home, Buscar, Criar, Urgente, Perfil) rodam dentro da estrutura com a barra inferior, enquanto telas como "Detalhes do Serviço" rodam por cima, ocupando a tela cheia.
* **Gerenciamento de Estado (ViewModel):** Centralizamos as listas (`Servico` e `Proposta`) em um ViewModel para separar a regra de negócios da interface visual (UI).

---

## 4. Qual foi a complexidade extra que o trio colocou na tela de Detalhes, e por que escolheram justamente essa?

Decidimos colocar a complexidade na **Tela de Descrição do Serviço** pois ela é o coração da interação entre o cliente e o prestador no aplicativo. 

Ela não apenas exibe dados reescritos, mas:
1. Recebe o ID do serviço clicado e busca os dados em tempo real.
2. Faz o cruzamento de dados com a lista de propostas.
3. Possui botões interativos e envia novas informações de volta para o ViewModel (como cadastrar um novo orçamento para aquele serviço específico).

![Tela Descrição do Serviço](materiaisProgesso/TelaDescricao2.0.png)

---

## 5. Algum integrante teve dificuldade em algum ponto? Como resolveram?

A maior dificuldade que o trio enfrentou foi fazer o `Navigation` funcionar e integrar ele entre todas as telas de forma fluida. 

**Como resolvemos:**
Tivemos que estudar a fundo como o `NavHost` funciona no Jetpack Compose, aprendendo a passar argumentos entre rotas (como o ID de um serviço) e a estruturar a navegação para que a `BottomBar` não desaparecesse nas abas principais e não atrapalhasse as telas de detalhes.