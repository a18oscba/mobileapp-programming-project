Min idé med appen är att vi lever i en värld där deet är populärt att resa runt. Jag tänkte att man kan ha ett par länder där när man trycker på en av dem visas lite fakta om dem som tex terräng och geografisk plats.

Dem följande bilderna visar hur appen ser ut frånstart, vid tryck av objekt i listan och about sidan.

![image](https://user-images.githubusercontent.com/62877630/235787458-1c64ce80-3d58-4e41-8862-f5dbe1b8c646.png)
![image](https://user-images.githubusercontent.com/62877630/235787524-e4cf5075-ee1e-4ccb-b53f-d2b9eef2f8b4.png)

Listan är simpelt designad av nedan kod:
```
<TextView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:background="@android:color/white"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/list_item_textview.xml">
</TextView>
```

Informationen ovan visas med hjälp av kod snutten nedan, den hämtar informationen lagrad på servern med hjälp av getter funtionerna i TravelNations klassen.
```
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String message = "The town" +
                        " " + travelNations.get(i).getName() + " is located in " + travelNations.get(i).getLocation() +
                        " and the terrain consists mostly of " + travelNations.get(i).getCategory();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
```
Nedan är en kodsnutt som omderigerar dig till about sidan som förklarar syftet med applikationen:
```
    public void GoToAbout(){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
```
Med hjälp av den funktionen så kan vi se sidan nedan.

![image](https://user-images.githubusercontent.com/62877630/235788502-0db7727f-17ab-4cc0-ba9c-9338706d60aa.png)
