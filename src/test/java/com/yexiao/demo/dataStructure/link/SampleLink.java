package com.yexiao.demo.dataStructure.link;

/**
 * @author xuhf
 * @date 2021/1/20 17:29
 **/
public class SampleLink<E> {

    private LinkItem<E> first;
    private LinkItem<E> last;
    private int length = 0;

    public int size(){
        return length;
    }

    public void add(E e){
        LinkItem<E> item = new LinkItem<E>(e);
        if(length == 0){
            this.first = item;
            this.last = item;
        }
        item.setNext(first);
        item.setPrev(last);
        first.setPrev(item);
        this.last = item;
        item.getPrev().setNext(item);
        length++;
    }

    public E get(int index){
        LinkItem<E> item = first;
        for(int i=0; index < length && i<index;i++){
            item = item.next;
        }
        return item.getData();
    }

    private class LinkItem<E>{
        private LinkItem prev;
        private LinkItem next;
        private E data;

        public LinkItem(E data){
            this.data = data;
        }

        public LinkItem getPrev() {
            return prev;
        }

        public void setPrev(LinkItem prev) {
            this.prev = prev;
        }

        public LinkItem getNext() {
            return next;
        }

        public void setNext(LinkItem next) {
            this.next = next;
        }

        public E getData() {
            return data;
        }
    }

}
