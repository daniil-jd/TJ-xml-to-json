# TJ-xml-to-json
Web-приложение загружает xml-файл на сервер и выводить в формате json.
Пример xml-файла:
<root>
    <nodeA>Node 1</nodeA>
    <nodeB>
        <nodeC>Node 2</nodeC>
        <nodeD>Node 3</nodeD>
        <nodeE>
            <nodeF>Node 4</nodeF>
        </nodeE>
        <nodeG>
            <nodeH>
                <nodeI>Node 5</nodeI>
            </nodeH>
        </nodeG>
    </nodeB>
    <nodeC1>
    	<nodeC2>Node 6.1</nodeC2>
    </nodeC1>
    <nodeD1></nodeD1>
</root>

Полученный json:
{
  "name": "root",
  "value": 21.1,
  "childList": [
    {
      "name": "nodeA",
      "value": 1.0
    },
    {
      "name": "nodeB",
      "value": 14.0,
      "childList": [
        {
          "name": "nodeC",
          "value": 2.0
        },
        {
          "name": "nodeD",
          "value": 3.0
        },
        {
          "name": "nodeE",
          "value": 4.0,
          "childList": [
            {
              "name": "nodeF",
              "value": 4.0
            }
          ]
        },
        {
          "name": "nodeG",
          "value": 5.0,
          "childList": [
            {
              "name": "nodeH",
              "value": 5.0,
              "childList": [
                {
                  "name": "nodeI",
                  "value": 5.0
                }
              ]
            }
          ]
        }
      ]
    },
    {
      "name": "nodeC1",
      "value": 6.1,
      "childList": [
        {
          "name": "nodeC2",
          "value": 6.1
        }
      ]
    },
    {
      "name": "nodeD1",
      "value": 0.0
    }
  ]
}
