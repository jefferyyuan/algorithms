package set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * This class is to provide a data structure for disjoint sets.
 * It supports basic union and find opeartions.
 *
 * This data structure is implemented by using a backing Map,
 * nevertheless, it can be implemented by using a backing Array or Set.
 *
 * The upside is the fact the using a Map can significantly decrease the running time
 * of the getNode operation which is often used to get the reference of the node by giving the object itself.
 * However, the downside is using a backing map will increase the memory usage since the map keep tracks of
 * the nodes and corresponding objects.
 *
 * Because the backing data structure is a Map, so this class does not allow duplicate objects
 *
 * Some of the codes referenced Introduction to Algorithms by Cormen et al.
 */
public class UnionFind {
        private Map> map;
        /**
         * Default constructor
         */
        public UnionFind(){
                map = new HashMap>();
        }
        public UnionFind(Collection input){
                this();
                for(Object o: input){
                        makeSet((T) o);
                }              
        }
        /**
         * Internal method for add to create a new UnionFindNode
         * @param data
         */
        private void makeSet(T data){
                UnionFindNode root = new UnionFindNode(data);
                root.rank = 0;
                map.put(data, root);
        }
        /**
         * Add a new set to this structure
         * @param data An object put in the set
         */
        public void add(T data){
                makeSet(data);
        }
        /**
         * This method returns the root representative of the given object
         * @param target Target object
         * @return The representative of the set which contains the target object
         */
        public T find(T target){
                return find(getNode(target)).data;
        }
        /**
         * Union two sets
         * @param t1
         * @param t2
         */
        public void union(T t1, T t2){
                union(getNode(t1), getNode(t2));
        }
        /**
         * Internal method to perform the find operation
         * @param target Given object
         * @return The representative of the set which contains the given object
         */
        private UnionFindNode find(UnionFindNode target){
                if(target != target.parent)
                        target.parent = find(target.parent);
                return target.parent;
        }
        /**
         * Link two node by applying path compression and union-by-rank strategies.
         * The order of the two arguments does not matter.
         * @param x Node 1
         * @param y Node 2
         */
        private void link(UnionFindNode x, UnionFindNode y){
                if(x.rank > y.rank){
                        //link y to x
                        y.parent = x;
                }else if(x.rank < y.rank){
                        //link x to y
                        x.parent = y;
                }else{
                        y.parent = x;
                        x.rank++;
                }
        }
        /**
         * Internal method to perform union operaion by calling link
         * function on the representative nodes of the two given node.
         * @param x Node 1
         * @param y Node 2
         */
        private void union(UnionFindNode x, UnionFindNode y){
                if(x==y)
                        throw new IllegalArgumentException("You can not union only 1 data");
                link(find(x), find(y));
        }
        /**
         * This method is to search and return the node based on the given object by iterating the backing set
         * @param o The object to be found
         * @return The node which contains the object
         * @throws NoSuchElementException if the backing map doesn't containt the object
         */
        private UnionFindNode getNode(T o){
                UnionFindNode node = map.get(o);
                if(node==null)
                        throw new NoSuchElementException();
                return node;
        }
        /**
         * Check if there is a path that connects all the nodes to a single root
         * @return true if the statement above exists; false otherwise
         */
        public boolean checkSinglePathContainAllNode(){
                //make a copy of the backing set for not compromising the original data
                Set> copy = new HashSet>();
                copy.addAll(map.values());
                int check = 0;
                for(UnionFindNode node: copy){
                        if(node.parent == node){
                                if(++check > 1)
                                        return false;
                        }
                }
                return true;
        }
        /**
         * Given a data, show the path from the start point to its root
         *
         * @param data
         * @return
         */
        public List findPath(T data){
                List path = new ArrayList();
                UnionFindNode start = getNode(data);
                while(start != start.parent){
                        path.add(start.data);
                        start = start.parent;
                }
                path.add(find(start).data);
                return path;
        }
}