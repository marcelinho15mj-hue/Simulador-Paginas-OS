# Simulador de Algoritmos de Substituição de Páginas

Trabalho prático de Sistemas Operacionais — simulador dos principais algoritmos de substituição de páginas em memória virtual.

- Marcelo Kalsovik Junior - 2413541
- Bernardo Batista - 2413539

## Algoritmos Implementados
| # | Algoritmo | Estratégia |
|---|-----------|------------|
| 1 | FIFO | Remove a página mais antiga |
| 2 | LRU | Remove a menos recentemente usada |
| 3 | Ótimo | Remove a usada mais distante no futuro |
| 4 | Clock | Segunda chance com bit de referência |

## Pré-requisitos
- Java JDK 8 ou superior

## Como Executar
# 1. Abra
Abra o arquivo 

# 2. Rode
Aperte em run para rodar o programa.

## Resultados (cadeia padrão, 4 frames)
| Algoritmo | Faltas |
|-----------|--------|
| FIFO      | 10     |
| LRU       | 8      |
| Ótimo     | 8      |
| Clock     | 9      |