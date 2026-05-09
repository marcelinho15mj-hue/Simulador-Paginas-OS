# ⚡ Simulador de Substituição de Páginas (SDPM Style)

Este projeto é um simulador de algoritmos de substituição de páginas em memória virtual, desenvolvido para a disciplina de **Sistemas Operacionais** na **UNIFOR**. A aplicação foca em demonstrar de forma visual e prática como diferentes estratégias de gestão de memória impactam a performance do sistema.

---

## 👨‍💻 Autores
- Marcelo Kalsovik Junior - 2413541
- Bernardo Batista - 2413539

---

## 🎨 Diferencial: Interface Dark Neon
Diferente dos simuladores comuns em terminal, este projeto conta com uma interface gráfica (GUI) moderna inspirada no sistema **SDPM**, utilizando:
- **Dark Mode** para melhor conforto visual.
- **Gráficos Dinâmicos** para comparação de eficiência.
- **Centralização Absoluta** com `GridBagLayout`.

![Preview da Interface](https://github.com/marcelinho15mj-hue/Simulador-Paginas-OS/blob/main/caminho-para-sua-imagem.png)

---

## 🧠 Algoritmos Implementados

O simulador compara quatro lógicas fundamentais:

1.  **FIFO (First-In, First-Out):** Substitui a página que está na memória há mais tempo.
2.  **LRU (Least Recently Used):** Substitui a página que não é utilizada há mais tempo (localidade temporal).
3.  **Ótimo:** O benchmark teórico que substitui a página que levará mais tempo para ser usada no futuro.
4.  **Clock:** Uma evolução do FIFO que utiliza um bit de "segunda chance" para aumentar a eficiência.

---

## 📊 Resultados e Performance
Simulação padrão com a cadeia `{7, 0, 1, 2, 0, 3, 0, 4}` e **3 quadros**:

| Algoritmo | Faltas de Página | Porcentagem de acerto |
| :--- | :--- | :--- |
| **FIFO** | 7 | 12,5% |
| **LRU** | 6 | 25% |
| **Ótimo** | 6 | 25% |
| **Clock** | 6 | 25% |

---

## 🚀 Como Executar

### Pré-requisitos
- Java JDK 8 ou superior instalado.

### Passo a Passo
1. Clone o repositório:
   ```bash
   git clone [https://github.com/marcelinho15mj-hue/Simulador-Paginas-OS.git](https://github.com/marcelinho15mj-hue/Simulador-Paginas-OS.git)

2. Compile o projeto:
javac src/*.java -d out

3. Execute a aplicação:
java -cp out SimuladorGUI