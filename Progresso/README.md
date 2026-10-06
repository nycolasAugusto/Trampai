# Trampai (Taskly)

Aplicativo Android feito em **Jetpack Compose** que conecta quem precisa de um serviço com quem pode fazê-lo. O cliente publica um serviço, os profissionais enviam propostas e o cliente compara as ofertas.

Este é o nosso **Trabalho 2** da disciplina. O app evoluiu do protótipo estático do Trabalho 1 para uma versão com listas reativas, formulários que funcionam e navegação completa entre as telas.

A história das nossas decisões, com prints e vídeos, está no **[PROCESSO.md](PROCESSO.md)**.

---

## Como rodar

1. Instale o **Android Studio** (uma versão recente).
2. Clone este repositório e abra a pasta do projeto no Android Studio (**File > Open**).
3. Espere o Gradle terminar de sincronizar. Na primeira vez ele baixa as dependências, então precisa de internet.
4. Escolha onde rodar:
   - **Celular Android:** ative a *Depuração USB* e conecte o cabo; ou
   - **Emulador:** crie um em *Device Manager* e inicie.
5. Clique em **Run** (botão verde ▶).

> A versão mínima do Android aceita está no `minSdk` do arquivo `app/build.gradle.kts`.

### Dependências

Já estão declaradas no Gradle, então basta sincronizar:

- Jetpack Compose + Material 3
- Navigation Compose (`navigation-compose`)
- ViewModel para Compose (`lifecycle-viewmodel-compose`)
- Material Icons Extended (`material-icons-extended`)

---

## Roteiro rápido para testar

1. Na aba **Criar**, preencha um serviço e toque em *Publicar Serviço*. Ele aparece no topo da **Início**.
2. Toque no card do serviço para abrir a **Descrição**.
3. Toque em *Enviar Proposta*, informe o valor por hora e uma mensagem. O app abre a lista de **Propostas**.
4. No card da proposta, veja se o valor ficou acima ou abaixo do que o serviço pede, quem pediu e a hora do envio.
5. **Segure** um card (na Início ou nas Propostas) para removê-lo.
6. Passe pelas abas **Buscar**, **Urgente** e **Perfil** pela barra inferior.

---

## Organização do código

```
app/src/main/java/com/example/trampai/
├── MainActivity.kt          ponto de entrada, chama o AppNavigation
├── MeuViewModel.kt          data classes (Servico, Proposta) e as listas reativas
├── navigation/              rotas, NavHost, barra inferior e abas
└── Tela*.kt                 uma tela por arquivo
```

> Os dados ficam só na memória. Ao fechar o app, o que foi criado se perde. Isso é esperado nesta etapa do trabalho.

---

## Requisitos do trabalho

| Requisito | Situação |
|---|---|
| Pelo menos 7 telas navegáveis | Sim (7 telas) |
| `NavHost` central e objeto `Rotas` | Sim |
| `NavigationBar` funcionando | Sim (5 abas) |
| 2 data classes diferentes | Sim (`Servico` e `Proposta`) |
| 2 telas de lista com `LazyColumn` + `Card` | Sim (Início e Propostas) |
| Adicionar item pela interface, nas duas listas | Sim |
| Remover item pela interface, nas duas listas | Sim (clique longo) |
| Detalhe recebendo o item certo por argumento de rota | Sim, na Descrição do Serviço |
| Detalhe com algo a mais do que reexibir campos | Sim (veja o PROCESSO.md) |
| Segunda tela de detalhe, para a lista de propostas | Em andamento: hoje as propostas mostram os dados cruzados no próprio card |
| `TopAppBar` com botão de voltar | Em andamento: hoje o voltar usa um botão próprio |