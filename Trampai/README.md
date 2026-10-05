# 🛠️ Trampai (Taskly) - Trabalho 2

Aplicativo Android desenvolvido em **Jetpack Compose** para conectar prestadores de serviço e clientes, evoluindo do protótipo estático do Trabalho 1 para uma aplicação funcional, reativa e navegável.

---

## 🚀 Como Rodar o Projeto

1. Certifique-se de ter o **Android Studio** instalado (Ladybug ou superior recomendado).
2. Clone ou abra este repositório no Android Studio.
3. Aguarde a sincronização do **Gradle** (`gradle_sync`).
4. Conecte um dispositivo Android físico com Depuração USB ativada ou inicie um Emulador Android (API 24+ recomendada).
5. Clique no botão **Run (▶)** para compilar e executar o aplicativo (`app:assembleDebug`).

---

## 📖 Documentação do Processo e Decisões (Seção 4)

### 1. Como estava o projeto no Trabalho 1 e o que mudou?
* **No Trabalho 1:** O aplicativo possuía telas estáticas (mockups) com navegação básica e dados fixos na tela, sem persistência em memória reativa, sem formulários funcionais de cadastro e sem passagem de parâmetros reais entre telas.
* **No Trabalho 2:** O projeto foi totalmente refatorado para arquitetura moderna com **Jetpack Compose**. Adicionamos listas reativas (`mutableStateListOf`), formulários com validação de campos (`OutlinedTextField`), navegação dinâmica com passagem de argumentos por rota (`NavHost` e `NavController`), múltiplos tipos de `data classes` (`Servico` e `Proposta`) e uma barra de navegação inferior (`NavigationBar`) totalmente funcional.

### 2. Por que essas telas novas? O que cada uma faz?
O app possui **7 telas navegáveis principais**, organizadas para cobrir todo o fluxo do usuário:
1. **Aba Início (`TelaHome`):** Lista principal de serviços disponíveis (`LazyColumn` + `Card`), com opção de exclusão e navegação para detalhes.
2. **Aba Buscar (`TelaBuscaServicos`):** Tela de categorias e busca de serviços.
3. **Aba Criar (`TelaCriarServico`):** Formulário completo com validação para o usuário publicar um novo serviço na lista reativa.
4. **Aba Urgente (`TelaServicoRelampago`):** Tela de chamados rápidos/relâmpago.
5. **Aba Perfil (`TelaPerfil`):** Perfil do usuário, estatísticas e atalho para propostas.
6. **Tela de Descrição do Serviço (`TelaDescricaoServico`):** Tela de Detalhes 1. Exibe informações completas do serviço selecionado através de argumento de rota (`servicoId`).
7. **Tela de Propostas de Servidores (`TelaPropostasServidores`):** Lista de propostas enviadas, permitindo gerenciar e remover propostas.

### 3. Decisões de configuração e organização do código
* **Estrutura de Rotas Centralizada:** Criamos um objeto `Rotas` em `navigation/Rotas.kt` contendo todas as strings de rota como `const val`, além de funções auxiliares (como `Rotas.descricao(id)`) para montagem dinâmica de rotas com parâmetros.
* **NavHost Aninhado (Abas vs. Telas Cheias):** 
  * O `AppNavigation` (nível superior) gerencia as telas de navegação completa (Detalhes e Propostas).
  * O `MinhaTela` gerencia um `NavHost` interno para as 5 abas da barra inferior (`NavigationBar`), mantendo o estado das abas intacto ao navegar entre elas.
* **ViewModel Centralizado:** Toda a lógica de estado reativa (`mutableStateListOf` para serviços e propostas) e operações de CRUD em memória residem no `MeuViewModel`.

### 4. Complexidade extra na tela de detalhes
A exigência da seção 3.2 era que pelo menos uma tela de detalhes fizesse mais do que apenas reexibir os campos. Na **`TelaDescricaoServico`**, implementamos:
* **Combinação de dados entre as duas listas:** A tela busca o serviço pelo ID passado na rota e calcula dinamicamente quantas propostas foram enviadas para aquele serviço (`qtdPropostas`).
* **Navegação secundária / Ação interativa:** Permite ao usuário abrir um diálogo de formulário (`DialogProposta`), preencher valores e enviar uma nova proposta que é salva instantaneamente na lista reativa de propostas.

### 5. Dificuldades e soluções do trio
* **Desafio:** Gerenciar a navegação entre o `NavController` global de telas cheias e o `NavHost` interno das abas inferiores (`navInterno`).
* **Solução:** Definimos uma regra clara de arquitetura: navegação entre abas usa `navInterno.irParaAba()`, enquanto navegação para fluxos externos (como detalhes e propostas) usa o `navController` principal.

---

## 🎯 Checklist de Requisitos Atendidos

- [x] Repositório estruturado com README e documentação do processo
- [x] App com 7 telas navegáveis, todas funcionais
- [x] `NavigationBar` (BottomNavigation) funcionando corretamente
- [x] 2 `data classes` diferentes (`Servico` e `Proposta`)
- [x] 2 telas de Lista usando `LazyColumn` + `Card` + `mutableStateListOf`
- [x] Formulários para ADICIONAR novos itens via UI (`TextField` / `OutlinedTextField`)
- [x] Funcionalidade para REMOVER itens pela UI
- [x] 2 telas de Detalhes/Gestão com passagem de dados reais (argumento de rota)
- [x] Complexidade extra na tela de detalhes (cálculo de propostas + envio interativo)
- [x] Objeto `Rotas` com rotas nomeadas
- [x] Botões de voltar funcionais (`popBackStack()`)
