package com.org;

public class HashTable<K,V>{
    private int size = 100;
    private Table<K,V>[] table;
    private Table latestOperatedEntry;


    static class Table<K, V> {
        K key;
        V value;
        Table<K,V> next;

        public Table(K key, V value, Table<K,V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public HashTable(){
        table = new Table[size];
    }

    private int hash(K key){
        return Math.abs(key.hashCode()) % size;
    }

    public void put(K newKey, V data){
        if(newKey==null)
            return;
        int hash=hash(newKey);
        Table<K,V> newEntry = new Table<K,V>(newKey, data, null);
        latestOperatedEntry = newEntry;

        if(table[hash] == null){
            table[hash] = newEntry;
        }else{
            Table<K,V> previous = null;
            Table<K,V> current = table[hash];

            while(current != null){
                if(current.key.equals(newKey)){
                    if(previous==null){
                        newEntry.next=current.next;
                        table[hash]=newEntry;
                        return;
                    }
                    else{
                        newEntry.next=current.next;
                        previous.next=newEntry;
                        return;
                    }
                }
                previous=current;
                current = current.next;
            }
            previous.next = newEntry;
        }
    }


    public V get(K key){
        int hash = hash(key);
        if(table[hash] == null){
            return null;
        }else{
            Table<K,V> temp = table[hash];
            while(temp!= null){
                if(temp.key.equals(key)){
                    latestOperatedEntry = temp;
                    return temp.value;
                }

                temp = temp.next;
            }

            return null;
        }
    }

    public void evict(){
        K k = (K)latestOperatedEntry.key;
         remove(k);
    }


    public V remove(K deleteKey){
        V value = null;
        int hash=hash(deleteKey);

        if(table[hash] == null){
            return null;
        }else{
            Table<K,V> previous = null;
            Table<K,V> current = table[hash];

            while(current != null){
                if(current.key.equals(deleteKey)){
                    value = current.value;
                    if(previous==null){
                        table[hash]=table[hash].next;
                        return value;
                    }
                    else{
                        previous.next=current.next;
                        return value;
                    }
                }
                previous=current;
                current = current.next;
            }
            return value;
        }

    }

}

