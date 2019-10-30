package com.vortexbird.seguridad.modelo;

import javax.swing.tree.DefaultMutableTreeNode;
import org.primefaces.model.TreeNode;

public class NodeUserObject{
	
    protected DefaultMutableTreeNode wrapper;
    protected String text;
    protected boolean expanded;
    protected String tooltip;
    protected String action;
    protected TreeNode treeNode;
    protected String leafIcon;
    protected String branchExpandedIcon;
    protected String branchContractedIcon;
    protected String icon;
    protected boolean leaf;
    private int rowIndex;
    

    public NodeUserObject(DefaultMutableTreeNode defaultMutableTreeNode) {
        super();
        setLeafIcon("tree_document.gif");
        setBranchContractedIcon("tree_folder_closed.gif");
        setBranchExpandedIcon("tree_folder_open.gif");
        setExpanded(true);
    }
    
    public int getRowIndex()
    {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex)
    {
        this.rowIndex = rowIndex;
    }

    public void setLeaf(boolean leaf)
    {
        this.leaf = leaf;
    }

    public boolean isLeaf()
    {
        return leaf;
    }

    public String action()
    {
        return action;
    }

    public void setTreeNode(TreeNode treeNode)
    {
        this.treeNode = treeNode;
    }

    public TreeNode getTreeNode()
    {
        return treeNode;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getFamily()
    {
        return null;
    }

    public boolean isExpanded()
    {
        return expanded;
    }

    public void setExpanded(boolean isExpanded)
    {
        expanded = isExpanded;
    }

    public String getTooltip()
    {
        return tooltip;
    }

    public void setTooltip(String tooltipString)
    {
        tooltip = tooltipString;
    }

    public String toString()
    {
        return text;
    }

    public String getLeafIcon()
    {
        return leafIcon;
    }

    public void setLeafIcon(String leafIcon)
    {
        this.leafIcon = leafIcon;
    }

    public String getBranchContractedIcon()
    {
        return branchContractedIcon;
    }

    public void setBranchContractedIcon(String branchContractedIcon)
    {
        this.branchContractedIcon = branchContractedIcon;
    }

    public String getBranchExpandedIcon()
    {
        return branchExpandedIcon;
    }

    public void setBranchExpandedIcon(String branchExpandedIcon)
    {
        this.branchExpandedIcon = branchExpandedIcon;
    }

    public String getIcon()
    {
        if(isLeaf())
        {
            if(leafIcon != null)
                return leafIcon;
        } else
        if(isExpanded())
        {
            if(branchExpandedIcon != null)
                return branchExpandedIcon;
        } else
        if(branchContractedIcon != null)
            return branchContractedIcon;
        return icon;
    }

    public DefaultMutableTreeNode getWrapper()
    {
        return wrapper;
    }

    public void setWrapper(DefaultMutableTreeNode wrapper)
    {
        this.wrapper = wrapper;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }
}