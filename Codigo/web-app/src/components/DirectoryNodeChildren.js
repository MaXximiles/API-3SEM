import React from "react";
import DirectoryNode from "./DirectoryNode";

const DirectoryNodeChildren = ({ children, collapsedNodes, collapseNode, createChild }) => {
  const renderedChildren = children.map((child) => {
    return (
      <DirectoryNode
        key={child.docId}
        node={child}
        collapsedNodes={collapsedNodes}
        collapseNode={collapseNode}
        createChild={createChild}
      />
    );
  });

  return <div className="folder-list ui list">{renderedChildren}</div>;
};

export default DirectoryNodeChildren;
