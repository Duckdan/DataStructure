package com.study.yang.genericparadigm

import kotlin.collections.ArrayList

/**
 * 哈夫曼树
 */
class HuffmanTree {

    /**
     * 节点：让其成为可比的，根据权重排序
     */
    class Node<T>(var data: T?, var weight: Int) : Comparable<Node<T>> {

        //左节点的权重
        var leftNode: Node<T>? = null
        //右结点的权重
        var rightNode: Node<T>? = null

        override fun compareTo(other: Node<T>): Int {
            return this.weight - other.weight
        }

        override fun toString(): String {
            return "Node(data=$data, weight=$weight)"
        }

    }

    fun <T> createHuffmanTree(nodes: ArrayList<Node<T>>): Node<T>? {
        //nodes等于1的时候表示只根节点了
        while (nodes.size > 1) {
            nodes.sort() //根据结点权重排序
 //           println(nodes)
            val leftNode = nodes[0]
            val rightNode = nodes[1]
            var parentNode = Node<T>(null, leftNode.weight + rightNode.weight)
            parentNode.leftNode = leftNode
            parentNode.rightNode = rightNode
            nodes.removeAt(0)//移除掉第一个元素
            nodes.removeAt(0)//移除掉第二个元素
            //把生成的父节点放到集合的最后面
            nodes.add(parentNode)
        }
        return if (nodes.isEmpty()) null else nodes[0]
    }

    /**
     * 递归打印哈夫曼树
     */
    fun <T> printTree(node: Node<T>?) {
        println(node)
        if (node?.leftNode != null) {
            print("left:")
            printTree(node.leftNode)
        }

        if (node?.rightNode != null) {
            print("right:")
            printTree(node.rightNode)
        }
    }
}

fun main() {
    var huffmanTree = HuffmanTree()
    var nodes = arrayListOf<HuffmanTree.Node<String>>()
    nodes.add(HuffmanTree.Node("a", 8))
    nodes.add(HuffmanTree.Node("b", 10))
    nodes.add(HuffmanTree.Node("c", 7))
    nodes.add(HuffmanTree.Node("d", 11))
    nodes.add(HuffmanTree.Node("e", 19))
    nodes.add(HuffmanTree.Node("f", 17))
    nodes.add(HuffmanTree.Node("g", 1))
    val tree = huffmanTree.createHuffmanTree(nodes)
    huffmanTree.printTree(tree)

}