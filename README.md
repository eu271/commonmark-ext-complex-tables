# commonmark-ext-complex-tables
Custom table extension for the proyect https://github.com/atlassian/commonmark-java


```
#@+TABLE_START
-1, 1
| A | B |
-1, 3
| a | b1 | b2 | b3 |
| a | b1 | b2 | b3 |
| a | b1 | b2 | b3 |
| a | b1 | b2 | b3 |
-1, 3
| a | b1 | b2 | b3 |
-1, 3
| a | b1 | b2 | b3 |
-2, 2
| a'| b1' | b2' | b3'|
#@+TABLE_END
```

<table style="width:100%">
<tr>
<td colspan="6"></br>A</td>
<td colspan="6"></br>B</td>
</tr>
<tr>
<td colspan="6"></br>a</br>a</br>a</br>a</td>
<td colspan="2"></br>b1</br>b1</br>b1</br>b1</td>
<td colspan="2"></br>b2</br>b2</br>b2</br>b2</td>
<td colspan="2"></br>b3</br>b3</br>b3</br>b3</td>
</tr>
<tr>
<td colspan="6"></br>a</td>
<td colspan="2"></br>b1</td>
<td colspan="2"></br>b2</td>
<td colspan="2"></br>b3</td>
</tr>
<tr>
<td colspan="6"></br>a</td>
<td colspan="2"></br>b1</td>
<td colspan="2"></br>b2</td>
<td colspan="2"></br>b3</td>
</tr>
<tr>
<td colspan="3"></br>a'</td>
<td colspan="3"></br>b1'</td>
<td colspan="3"></br>b2'</td>
<td colspan="3"></br>b3'</td>
</tr>
<tr></tr>
</table>
