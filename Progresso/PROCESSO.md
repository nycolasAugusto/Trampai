# Trampai (Taskly): processo e decisões do Trabalho 2

O **Trampai** (o nome que aparece na tela é *Taskly*) é um app Android feito em Jetpack Compose que liga quem precisa de um serviço, como limpeza, encanamento ou pintura, a quem pode fazê-lo. O cliente publica o serviço, os profissionais respondem com propostas e o cliente compara as ofertas.

Neste documento contamos como saímos do protótipo do Trabalho 1 até a versão atual: o que o app faz, quais telas existem, como organizamos os dados e o código, onde está a parte mais elaborada e o que ainda queremos melhorar.

---

## Vídeos

| Vídeo | O que mostra |
|---|---|
| [Versão 1 do app](materiaisProgesso/VERSAO1PROJETO.mp4) | Como o projeto estava no Trabalho 1 |
| [Planos da versão 2](materiaisProgesso/V2_PLANOS.mp4) | O que planejamos mudar e as escolhas que fizemos |
| [Planos finais da versão 2](materiaisProgesso/V2_PLANOS_FINAIS.mp4) | O que decidimos construir no fim |
| [Comprovacao PERSISTENCIA banco de dados](materiaisProgesso/BancoDeDadosFuncionando.mp4) | O que decidimos construir no fim |

> Se o vídeo não abrir direto no navegador, é só baixar o arquivo.

---

## 1. O que o app faz hoje

### O que já funciona

- **Publicar um serviço.** Na aba *Criar*, o formulário pede título, categoria, valor por hora, descrição, requisitos e localização. Ele valida o que foi digitado e avisa se faltar algo.
- **Ver os serviços publicados.** A aba *Início* mostra uma lista de cards. O serviço novo aparece no topo.
- **Remover um serviço.** Basta segurar o card (clique longo). As propostas daquele serviço somem junto, para não sobrar proposta sem serviço.
- **Abrir o detalhe de um serviço.** Ao tocar no card, a tela de Descrição mostra os dados do serviço que foi tocado, e não de outro.
- **Enviar uma proposta.** Na Descrição, o botão *Enviar Proposta* abre um formulário com o valor por hora e uma mensagem. Ao enviar, o app leva para a lista de propostas.
- **Comparar propostas.** Cada proposta mostra para qual serviço ela é, quem pediu o serviço, a hora do envio e se o valor está abaixo ou acima do que o serviço pede.
- **Remover uma proposta.** Também com clique longo.
- **Buscar categorias.** Na aba *Buscar*, o campo de texto filtra as categorias enquanto se digita.
- **Navegar.** Uma barra inferior com 5 abas (Início, Buscar, Criar, Urgente e Perfil), mais botões de voltar nas telas que abrem por cima.

### O que ainda é demonstrativo

Algumas telas já têm o visual pronto, mas com dados de exemplo:

- **Perfil:** nome, e-mail e os números do resumo são fixos.
- **Urgente:** a solicitação e o mapa são simulados.
- **Avaliação das pessoas:** a nota exibida é fixa.
- **Categorias:** tocar numa categoria abre a lista de propostas, ainda sem filtrar por categoria.

### Melhorias futuras

- Guardar os dados de forma que sobrevivam ao fechar o app (por exemplo, com Room).
- Ter contas de usuário, com login, para separar quem pede de quem atende.
- Uma tela própria para cada proposta, com botões para aceitar ou recusar.
- Mostrar no Perfil o serviço que está em andamento depois que uma proposta for aceita.
- Editar um serviço depois de publicado.
- Fazer a busca por categoria listar de verdade os serviços e profissionais daquela categoria.
- Ligar a tela Urgente a dados reais e a um mapa de verdade.
- Perfil com dados reais do usuário e avaliações.
- Fotos e anexos nos serviços.

---

## 2. As telas do aplicativo

Antes de programar, desenhamos um esboço das telas. As anotações do planejamento estão em [IdeiaTelas.txt](materiaisProgesso/IdeiaTelas.txt) e [TelasEscolhidasCanva.txt](materiaisProgesso/TelasEscolhidasCanva.txt).

<img src="materiaisProgesso/EsboçoTelas.png" alt="Esboço inicial das telas" width="520">

O app tem **7 telas**. Cinco delas ficam na barra inferior (as abas) e duas abrem por cima, em tela cheia.

```mermaid
flowchart TD
    Inicio["Início (aba)"] -->|"toque no card"| Descricao["Descrição do Serviço"]
    Inicio -->|"sino"| Urgente["Urgente (aba)"]
    Inicio -->|"ícone de perfil"| Perfil["Perfil (aba)"]
    Criar["Criar (aba)"] -->|"Publicar Serviço"| Inicio
    Buscar["Buscar (aba)"] -->|"toque na categoria"| Propostas["Propostas de Servidores"]
    Urgente -->|"Aceitar"| Descricao
    Descricao -->|"Enviar Proposta"| Propostas
    Perfil -->|"card Propostas de Servidores"| Propostas
```

### Abas (barra inferior)

**Início.** A lista de serviços publicados. É o ponto de partida, onde o usuário vê rápido as demandas ativas.

<img src="materiaisProgesso/telaHome2.0.png" alt="Tela Início" width="280">

**Buscar.** Categorias de serviço com um campo de busca que filtra enquanto o usuário digita.

<img src="materiaisProgesso/TelaBuscarServico2.0.png" alt="Tela Buscar" width="280">

**Criar.** O formulário para publicar um novo serviço, com validação dos campos.

<img src="materiaisProgesso/TelaCriarServico2.0.png" alt="Tela Criar Serviço" width="280">

**Urgente.** A tela de chamados emergenciais: o profissional vê a solicitação e escolhe entre recusar e aceitar.

<img src="materiaisProgesso/TelaJOBurgente2.0.png" alt="Tela Urgente" width="280">

**Perfil.** Dados do usuário, um resumo em cards e o atalho para as propostas.

<img src="materiaisProgesso/telaMeuPerfil2.0.png" alt="Tela Perfil" width="280">

### Telas abertas por cima (sem a barra inferior)

**Descrição do Serviço.** Os detalhes completos do serviço escolhido, com o botão para enviar uma proposta.

<img src="materiaisProgesso/TelaDescricao2.0.png" alt="Tela Descrição do Serviço" width="280">

**Propostas de Servidores.** A lista das propostas enviadas, com a comparação de valores. Abaixo, a tela com propostas e uma segunda captura.

<img src="materiaisProgesso/telaPropostas2.0-1.png" alt="Tela Propostas, captura 1" width="280"> <img src="materiaisProgesso/telaPropostas2.0-2.png" alt="Tela Propostas, captura 2" width="280">

---

## 3. Por que escolhemos as telas novas

O Trabalho 1 já tinha o começo do app (Início, Buscar, Criar e Urgente). Para o Trabalho 2 acrescentamos três telas, cada uma com um motivo.

**Propostas de Servidores.** Um serviço publicado só vira negócio quando alguém responde a ele. Sem esta tela, o app terminava no momento em que o cliente publicava o serviço. Ela é a segunda lista do trabalho e fecha o caminho: publicar, receber propostas, comparar.

**Descrição do Serviço.** O card da Início é um resumo; o cliente e o profissional precisam de um lugar para ver o serviço por inteiro. Também é nela que o profissional age: é de lá que sai a proposta. Por isso a escolhemos para mostrar a passagem de dados por rota.

**Meu Perfil.** Todo app com contas tem um lugar do usuário, e ele nos deu um ponto de entrada natural para a lista de propostas: o cliente abre o Perfil e vê as propostas que recebeu. Hoje ele mostra dados de exemplo, mas já está no lugar certo do fluxo, pronto para receber dados reais quando houver login.

---

## 4. As duas data classes

O app trabalha com dois tipos de item, e um depende do outro.

**`Servico`** é o pedido que o cliente publica.

```kotlin
data class Servico(
    val id: Int,
    val titulo: String,
    val categoria: String,
    val valorHora: Double,
    val descricao: String,
    val requisitos: String,
    val localizacao: String,
    val autor: String        // quem pediu o serviço (o requerente)
)
```

**`Proposta`** é a resposta de um profissional a um serviço.

```kotlin
data class Proposta(
    val id: Int,
    val servicoId: Int,         // liga a proposta ao serviço dela
    val servicoTitulo: String,
    val profissional: String,
    val valorHora: Double,
    val mensagem: String,
    val hora: String,           // preenchida na hora do envio
    val valorServico: Double,   // valor pedido pelo serviço, copiado na hora do envio
    val requerente: String      // quem pediu o serviço, copiado na hora do envio
)
```

Como elas se relacionam e por que as desenhamos assim:

- **O elo é o `servicoId`.** Cada proposta guarda o id do serviço a que pertence. É por ele que a Descrição conta quantas propostas o serviço recebeu.
- **Alguns campos são preenchidos sozinhos.** O usuário só digita o valor e a mensagem. A hora, o valor pedido e o requerente são gravados pelo `MeuViewModel` no momento em que a proposta é enviada. Assim a tela de propostas não precisa procurar nada na outra lista: já recebe tudo pronto.
- **Há valores calculados dentro da própria classe.** `valorFormatado` transforma `120.0` em `R$ 120/hr`, e `diferenca` e `textoDiferenca` comparam o valor da proposta com o valor pedido.
- **Remover um serviço remove as propostas dele.** Evita proposta "órfã", que apontaria para um serviço que não existe mais.

As duas listas ficam no `MeuViewModel` como `mutableStateListOf`. Quando algo é adicionado ou removido, as telas que leem a lista se atualizam sozinhas.

---

## 5. A parte que vai além de exibir dados

O enunciado pedia que alguma tela fizesse mais do que reexibir os campos de um item. Fizemos isso em duas partes que trabalham juntas, e o que construímos por último foi a segunda.

### Na Descrição do Serviço

- A tela recebe só o **id** pela rota (`descricao/{servicoId}`) e busca o serviço no ViewModel.
- Ela mostra "N proposta(s) enviada(s) para este serviço". Esse número não está guardado em lugar nenhum: é **calculado** contando, na lista de propostas, as que têm o `servicoId` daquele serviço. Ou seja, ela cruza as duas listas.
- Ela abre um formulário de proposta e devolve a nova proposta ao ViewModel.

### No card de cada proposta (a última parte que fizemos)

O cliente que recebe várias propostas quer saber logo qual compensa. Então cada card responde isso sem precisar abrir outra tela:

- **De qual serviço é**, no texto "Para: ...".
- **Quanto o valor está abaixo ou acima do pedido**, numa etiqueta colorida: verde quando é mais barata, laranja quando é mais cara, cinza quando é igual.
- **Quem pediu o serviço** (o requerente) e **a hora do envio**.

Um exemplo concreto: um serviço pede R$ 120/hr, e um profissional manda proposta de R$ 90/hr. O card mostra **"R$ 30/hr abaixo do pedido"**, em verde. O cálculo é uma subtração entre o valor da proposta e o valor pedido, guardado na proposta no momento do envio.

**Por que escolhemos isso.** Mostrar o serviço e o requerente já é cruzar as duas listas, e a diferença de valor é uma informação nova, que nenhum dos dois itens traz sozinho. Também é a parte do app que mais se parece com o problema real do usuário: decidir entre ofertas.

---

## 6. Do começo até agora

| | Trabalho 1 | Trabalho 2 |
|---|---|---|
| **Trocar de tela** | Uma variável de estado na `MainActivity` e um `when` escolhendo a tela | `NavHost` com rotas nomeadas, barra inferior e passagem de id por rota |
| **Dados** | Cards com textos escritos direto no código | Listas reativas (`mutableStateListOf`) no ViewModel |
| **Formulário de criar** | Campos desenhados, que não aceitavam digitação | `OutlinedTextField` com estado, validação e mensagem de erro |
| **Botões** | Vários sem ação (sino, categorias, "Ver Tudo", "Aceitar") | Todos levam a algum lugar |
| **Remover itens** | Não existia | Clique longo com aviso na tela |
| **Tipos de item** | Nenhum modelo de dados | `Servico` e `Proposta`, ligados por `servicoId` |
| **Ícones** | Emojis escritos como texto | `Icon` do Material |
| **Cores** | Textos sem cor definida, que sumiam no modo escuro | Cores definidas em cada tela |
| **Nomes** | `Tela1`, `Tela2`, `TelaUm`, `TelaDois`... | Nomes que dizem o que a tela é (`TelaHome`, `TelaCriarServico`...) |
| **Telas** | 4 | 7 |

Para ver a diferença em movimento, compare o [vídeo da versão 1](materiaisProgesso/VERSAO1PROJETO.mp4) com os [planos finais da versão 2](materiaisProgesso/V2_PLANOS_FINAIS.mp4).

---

## 7. Como organizamos o código

- **Rotas num lugar só.** O objeto `Rotas` guarda todas as rotas como `const val`, e funções como `Rotas.descricao(id)` montam a rota com o argumento. Assim não há texto solto espalhado pelo projeto.
- **Dois `NavHost`, um dentro do outro.** O de fora (`AppNavigation`) cuida das telas que abrem por cima. O de dentro, na tela que guarda as abas (`MinhaTela`), cuida da barra inferior. Foi o jeito de a barra aparecer só nas abas e sumir na Descrição e nas Propostas.
- **Uma regra para não errar o controlador.** Para ir de uma aba para outra, usamos o controlador de dentro (`navInterno`). Para abrir uma tela por cima, usamos o de fora (`navController`).
- **Um único ViewModel.** `MeuViewModel` guarda as duas listas e as operações de adicionar, remover e buscar por id. As telas só mostram o que recebem e avisam quando o usuário fez algo.
- **Telas sem dependência do `NavController`.** Cada tela recebe funções (`aoRemover`, `irParaBusca`...) em vez do controlador. Isso deixa as telas mais simples e fáceis de testar sozinhas.

---

## 8. Dificuldades e como resolvemos

- **Navegação.** A maior dificuldade foi fazer o `Navigation` funcionar entre todas as telas. Tivemos que entender como o `NavHost` funciona, como passar um argumento pela rota e como manter a barra inferior só nas abas. Resolvemos separando em dois `NavHost` e criando a regra do controlador certo para cada tipo de navegação.
- **Textos que sumiam.** No celular em modo escuro, vários textos ficavam quase invisíveis, porque não tinham cor definida. Passamos a definir a cor de cada texto.
- **Nomes de arquivos e funções.** Com `Tela1`, `Tela2`, `TelaUm` e `TelaDois`, ninguém achava o arquivo certo. Renomeamos tudo para nomes que dizem o que a tela faz.
- **Pacotes e imports.** Depois de mover arquivos para a pasta `navigation`, vários nomes ficaram "não encontrados" até acertarmos os imports.
- **Botões sem função.** O enunciado pede que todo botão tenha propósito. Revisamos o app inteiro e ligamos cada um a uma ação real.


## 🎬 Vídeos Demonstrativos

Abaixo estão os registros em vídeo que mostram a evolução do nosso aplicativo, desde a primeira versão até a entrega final. *(Se os vídeos não carregarem diretamente no visualizador, você pode baixar os arquivos locais para assistir).*

### 1. Versão 1 do App
Como o projeto estava estruturado no início, durante a entrega do Trabalho 1.

<video controls src="materiaisProgesso/VERSAO1PROJETO.mp4" title="Versão 1 do App" width="400"></video>

### 2. Planos da Versão 2
O que planejamos mudar, as novas funcionalidades pensadas e as escolhas iniciais que fizemos para melhorar a arquitetura do aplicativo.

<video controls src="materiaisProgesso/V2_PLANOS.mp4" title="Planos da Versão 2" width="400"></video>

### 3. Planos Finais da Versão 2
O que decidimos construir no fim, alinhando as expectativas iniciais com a implementação técnica real do projeto.

<video controls src="materiaisProgesso/V2_PLANOS_FINAIS.mp4" title="Planos Finais da Versão 2" width="400"></video>

### 4. Aplicativo Entregue (Versão Atual)
A demonstração do aplicativo em sua versão final.

<video controls src="materiaisProgesso/AppEntregue.mp4" title="Aplicativo Entregue" width="400"></video>

---

# Trabalho 3: persistência com Room

Até aqui, tudo o que o usuário criava morava na memória do aparelho. Bastava fechar o app para a lista voltar vazia. Neste trabalho trocamos a memória por um **banco de dados Room**: o que o usuário cria agora fica gravado no celular e continua lá quando o app abre de novo.

As seções acima descrevem o app do Trabalho 2. Daqui para baixo contamos o que mudou para chegar à versão com banco de dados.

> **Como usar os espaços de foto:** onde estiver escrito **[ESPAÇO PARA FOTO]**, salve a imagem na pasta `materiaisProgesso` com o nome sugerido, apague a linha em negrito e tire as marcas `<!--` e `-->` da linha de baixo.

---

## 1. O que mudou do Trabalho 2 para o Trabalho 3

| | Trabalho 2 | Trabalho 3 |
|---|---|---|
| **Onde os dados ficam** | Em listas na memória (`mutableStateListOf`) | Em um banco Room, num arquivo dentro do aparelho |
| **Fechar o app** | Perdia tudo | Os dados continuam lá |
| **Data classes** | Classes comuns | `@Entity`, que viram tabelas (`servicos` e `propostas`) |
| **Buscar** | A lista já estava pronta na memória | `LaunchedEffect(Unit)` chama uma função que usa `dao.buscarTodos()` |
| **Inserir e remover** | Mexiam só na lista | Gravam e apagam no banco, por um DAO, fora da thread principal |
| **Editar** | Não existia | `dao.atualizar(...)`, com um formulário de edição |
| **Detalhe do serviço** | Procurava o serviço na lista da memória | Recebe só o id pela rota e busca no banco (`buscarPorId`) |
| **Erros** | Sem tratamento | `try/catch` com `Log.e` em toda operação de banco |

As listas continuam reativas: a tela ainda lê um `mutableStateListOf`, mas agora quem preenche essa lista é o banco.

---

## 2. A estrutura do Room

### Dependências e a ordem certa

O Room precisa do **KSP**, um plugin que gera o código do banco na hora de compilar. A ordem importa: primeiro adicionamos o plugin e sincronizamos o Gradle; só depois, em outro passo, colocamos a linha `ksp(...)`. Se a linha do `ksp` entrar antes do plugin ser sincronizado, o Gradle não reconhece o comando.

```kotlin
plugins {
    // ...
    id("com.google.devtools.ksp") version "..."   // 1º passo: plugin, depois Sync
}

dependencies {
    // 2º passo: depois do Sync do plugin
    val room_version = "3.0.3"
    implementation("androidx.room3:room3-runtime:$room_version")
    ksp("androidx.room3:room3-compiler:$room_version")
}
```

Seguimos o exemplo da aula, cujo código importa `androidx.room3`. Por isso os artefatos têm o prefixo `room3`, e o `room-ktx` não é usado.

> **[ESPAÇO PARA FOTO]** Trecho do `build.gradle.kts` com o plugin do KSP e as dependências do Room.
>
> <!-- <img src="materiaisProgesso/T3_Gradle.png" alt="Gradle com Room e KSP" width="520"> -->

### As duas entidades

Cada data class do Trabalho 2 virou uma tabela. O `id` é gerado sozinho pelo banco.

```kotlin
@Entity(tableName = "servicos")
data class Servico(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    val categoria: String,
    val valorHora: Double,
    val descricao: String,
    val requisitos: String,
    val localizacao: String,
    val autor: String
)
```

`Proposta` segue o mesmo molde (tabela `propostas`) e guarda o `servicoId` do serviço a que pertence.

**Uma decisão importante:** os valores calculados (`valorFormatado`, `diferenca`, `textoDiferenca`) saíram de dentro das classes e viraram propriedades logo abaixo delas, no mesmo arquivo. Dentro da classe, o Room poderia confundi-los com colunas da tabela. Do lado de fora, as telas continuam usando `servico.valorFormatado` do mesmo jeito de antes.

### Os dois DAOs

Cada entidade tem o seu DAO, com os quatro métodos básicos. Todos são `suspend`.

```kotlin
@Dao
interface ServicoDAO {

    @Insert
    suspend fun inserir(servico: Servico)

    @Query("SELECT * FROM servicos ORDER BY id DESC")
    suspend fun buscarTodos(): List<Servico>

    @Update
    suspend fun atualizar(servico: Servico)

    @Delete
    suspend fun deletar(servico: Servico)

    // consulta filtrada: usada pela tela de Descrição
    @Query("SELECT * FROM servicos WHERE id = :id")
    suspend fun buscarPorId(id: Int): Servico?
}
```

O DAO de propostas tem os mesmos quatro métodos, mais `deletarPorServico`, que apaga todas as propostas de um serviço quando ele é removido.

### O banco

O `AppDatabase` reúne as entidades e expõe os DAOs. Usamos o padrão Singleton visto em aula (`@Volatile` mais `synchronized`) para existir uma única instância do banco no app inteiro.

```kotlin
@Database(entities = [Servico::class, Proposta::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun servicoDAO(): ServicoDAO
    abstract fun propostaDAO(): PropostaDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase { /* ... */ }
    }
}
```

> **[ESPAÇO PARA FOTO]** Painel de arquivos do Android Studio mostrando `Servico`, `Proposta`, os dois DAOs e o `AppDatabase`.
>
> <!-- <img src="materiaisProgesso/T3_ArquivosRoom.png" alt="Arquivos do Room no projeto" width="320"> -->

---

## 3. O CRUD completo, agora no banco

| Operação | Como funciona | Onde o usuário faz |
|---|---|---|
| **Buscar** | `LaunchedEffect(Unit)` chama uma função `suspend` que usa `buscarTodos()` quando a tela abre | Abas *Início* e *Propostas* |
| **Inserir** | `dao.inserir(...)` dentro de `CoroutineScope(Dispatchers.IO).launch` | Aba *Criar* (serviço) e o diálogo de proposta |
| **Atualizar** | `dao.atualizar(...)` dentro de `CoroutineScope(Dispatchers.IO).launch` | Ícone de lápis na *Descrição do Serviço* |
| **Remover** | `dao.deletar(...)` dentro de `CoroutineScope(Dispatchers.IO).launch` | Clique longo no card, na *Início* e nas *Propostas* |

### Buscar

Quando a tela abre, ela pede os dados ao banco e preenche a lista:

```kotlin
// na aba Início
LaunchedEffect(Unit) {
    viewModel.carregarServicos(db)
}

// no MeuViewModel
suspend fun carregarServicos(db: AppDatabase) {
    try {
        val lista = db.servicoDAO().buscarTodos()
        servicos.clear()
        servicos.addAll(lista)
    } catch (e: Exception) {
        Log.e("Trampai", "Erro ao buscar serviços", e)
    }
}
```

### Inserir, atualizar e remover

As três seguem o mesmo molde: a operação roda numa coroutine em `Dispatchers.IO`, para a tela não travar; fica dentro de um `try/catch`; e, no fim, a lista é recarregada para a tela mostrar o resultado.

```kotlin
fun adicionarServico(db: AppDatabase, servico: Servico) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            db.servicoDAO().inserir(servico)
            carregarServicos(db)
        } catch (e: Exception) {
            Log.e("Trampai", "Erro ao inserir serviço", e)
        }
    }
}
```

### Inserir na prática

> **[ESPAÇO PARA FOTO]** Aba *Criar* com o formulário preenchido.
>
> <!-- <img src="materiaisProgesso/T3_CriarServico.png" alt="Criar serviço preenchido" width="280"> -->

> **[ESPAÇO PARA FOTO]** Aba *Início* mostrando o serviço novo no topo da lista.
>
> <!-- <img src="materiaisProgesso/T3_InicioComItem.png" alt="Início com o serviço criado" width="280"> -->

### Editar na prática

O botão de lápis, no canto da *Descrição do Serviço*, abre o formulário **Editar serviço**, já preenchido com os dados atuais. Ao salvar, o app chama `atualizar` no banco e a tela mostra o valor novo. Foi a última peça do CRUD a ser implementada.

> **[ESPAÇO PARA FOTO]** Formulário *Editar serviço* aberto, com os dados atuais.
>
> <!-- <img src="materiaisProgesso/T3_EditarServico.png" alt="Editar serviço" width="280"> -->

> **[ESPAÇO PARA FOTO]** A *Descrição* (ou a *Início*) já com o dado alterado.
>
> <!-- <img src="materiaisProgesso/T3_ServicoEditado.png" alt="Serviço depois de editado" width="280"> -->

### Remover na prática

Segurar o card remove o serviço do banco. As propostas dele também saem (`deletarPorServico`), para não sobrar proposta ligada a um serviço que não existe mais.

> **[ESPAÇO PARA FOTO]** Aviso "Serviço removido" na tela, logo depois do clique longo.
>
> <!-- <img src="materiaisProgesso/T3_RemoverServico.png" alt="Serviço removido" width="280"> -->

---

## 4. A tela de detalhes lendo direto do banco

No Trabalho 2, a rota da *Descrição* já levava só o **id** (`descricao/{servicoId}`), mas a tela procurava o serviço numa lista da memória. Agora ela pergunta ao banco usando a consulta filtrada `buscarPorId`:

```kotlin
var servico by remember { mutableStateOf<Servico?>(null) }

LaunchedEffect(id) {
    servico = viewModel.buscarServicoPorId(db, id)   // dao.buscarPorId(id)
    viewModel.carregarPropostas(db)                  // para contar as propostas
}
```

Dois motivos nos fizeram gostar desse jeito:

- **A tela mostra o que está de fato no banco**, e não uma cópia que pode estar desatualizada.
- **Depois de editar um serviço**, a *Descrição* já mostra o valor novo e a *Início* também, porque ambas vêm da mesma fonte.

A informação que vai além de exibir dados continua lá: a *Descrição* conta, entre as propostas do banco, quantas pertencem àquele serviço, e o card da proposta compara o valor dela com o que o serviço pede.

> **[ESPAÇO PARA FOTO]** A tela de *Descrição* de um serviço, mostrando os dados dele e a contagem de propostas.
>
> <!-- <img src="materiaisProgesso/T3_DetalheDoBanco.png" alt="Descrição lendo do banco" width="280"> -->

---

## 5. O teste de aceitação: os dados sobrevivem?

Este é o teste que prova que o banco funciona. Passos que seguimos:

1. Criar um serviço e enviar uma proposta para ele.
2. Fechar o app **por completo**, tirando-o da lista de apps recentes.
3. Abrir o app de novo.
4. Conferir se o serviço e a proposta continuam lá.

> **[ESPAÇO PARA FOTO]** Antes de fechar: *Início* com o serviço criado.
>
> <!-- <img src="materiaisProgesso/T3_Aceitacao_1_Antes.png" alt="Antes de fechar o app" width="280"> -->

> **[ESPAÇO PARA FOTO]** O app fechado, na tela de apps recentes (ou saindo da lista).
>
> <!-- <img src="materiaisProgesso/T3_Aceitacao_2_Fechado.png" alt="App fechado" width="280"> -->

> **[ESPAÇO PARA FOTO]** Depois de reabrir: o mesmo serviço (e a proposta) ainda aparecendo.
>
> <!-- <img src="materiaisProgesso/T3_Aceitacao_3_Reaberto.png" alt="Dados após reabrir o app" width="280"> -->

> **[ESPAÇO PARA VÍDEO]** Gravação curta do teste completo.
>
> <!-- [Assistir ao teste de persistência](materiaisProgesso/T3_Room.mp4) -->

---

## 6. Decisões de organização do código no Trabalho 3

- **Mantivemos o ViewModel.** `MeuViewModel` continua guardando as listas que as telas leem, mas agora toda alteração passa pelos DAOs. As telas quase não mudaram.
- **O banco chega por parâmetro.** Quem precisa dele pega a instância com `AppDatabase.getDatabase(context)` e a passa ao ViewModel. Assim usamos só o que foi visto em aula.
- **A lista é recarregada depois de cada alteração.** Depois de inserir, atualizar ou remover, o ViewModel busca tudo de novo no banco. Fica simples e a tela sempre mostra o que está salvo.
- **Buscar usa `LaunchedEffect`; escrever usa `Dispatchers.IO`.** Seguimos a descrição do enunciado: a busca roda quando a tela abre, e as gravações rodam em `CoroutineScope(Dispatchers.IO).launch`.
- **O banco começa vazio.** Tiramos os dois serviços de exemplo que antes nasciam com o app. Se eles fossem recriados a cada abertura, voltariam depois de apagados e atrapalhariam o teste de persistência.
- **O "Aceitar" da tela Urgente leva à Início.** Antes ele abria o serviço de id 1, que pode não existir mais no banco.

---

## 7. O retorno do professor: quem cria o serviço também envia a proposta

Na apresentação anterior, o professor observou que, no nosso app, **quem cria o serviço é a mesma pessoa que envia a proposta**, o que não faz sentido num app que liga clientes a profissionais.

**Por que acontece.** O app ainda não tem contas nem login. Existe um único usuário fixo ("Alex Souza"), que ocupa os dois papéis: cliente, quando publica um serviço, e profissional, quando responde com uma proposta.

**O que decidimos.** Contas e perfis de usuário ficaram **fora do escopo** deste trabalho. O enunciado pede persistência com Room e um CRUD completo, e login seria um trabalho à parte, com telas e regras próprias. Preferimos entregar o banco de dados bem feito a abrir uma frente nova pela metade.

**Como resolveríamos depois:**

- Criar contas com login, cada uma com um papel (cliente ou profissional).
- Mostrar a opção *Enviar Proposta* só para quem é profissional.
- Impedir que alguém envie proposta para o próprio serviço.
- Ligar o *Perfil* a dados reais do usuário que fez login.

---

## 8. Dificuldades do Trabalho 3

- **Room 2 ou Room 3?** Começamos com os nomes de pacote e de artefato do Room 2 (`androidx.room`). O código da aula usa `androidx.room3`, então tivemos que trocar as dependências e todos os imports.
- **Classes repetidas.** Quando transformamos `Servico` e `Proposta` em `@Entity`, as versões antigas ainda estavam dentro do `MeuViewModel`. O Android Studio acusou *Redeclaration* e avisou que as propriedades novas estavam "sobrepostas". Resolvemos apagando as classes antigas.
- **A ordem do Gradle.** Foi preciso sincronizar o plugin do KSP antes de adicionar a linha `ksp(...)`; fora dessa ordem, o Gradle não reconhece o comando.
- **Valores calculados dentro da entidade.** Perceber que o `valorFormatado` e a `textoDiferenca` poderiam ser confundidos com colunas nos levou a movê-los para fora da classe.

---

## 9. O que ainda não fizemos

- **Contas de usuário** (veja a seção 7).
- **Edição de propostas.** Hoje só o serviço pode ser editado. Proposta só pode ser enviada e removida.
- **Propostas antigas após editar um serviço.** Elas guardam o valor que o serviço pedia na hora em que foram enviadas, então a comparação "abaixo/acima do pedido" não se atualiza se o valor do serviço mudar depois.
- **Tela própria para cada proposta**, com aceitar e recusar.
- **`TopAppBar` com botão de voltar.** Nossas telas de detalhe usam um botão de voltar próprio.
- **Perfil e Urgente com dados reais.** Continuam com dados de exemplo.

---

