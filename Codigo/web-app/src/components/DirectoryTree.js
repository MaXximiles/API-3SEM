import React, { useState, useEffect } from "react";
import restAPI from "../apis/restAPI";
import DirectoryNodeChildren from "./DirectoryNodeChildren";

const DirectoryTree = () => {
  const [content, setContent] = useState([]);
  const [shouldUpdateContent, setShouldUpdateContent] = useState(false);
  const [collapsedNodes, setCollapsedNodes] = useState([]);

  useEffect(() => {
    const directoryTree = async () => {
      const { data } = await restAPI.get("/codelist");

      setContent(data);
    };


    directoryTree();
  }, [shouldUpdateContent]);

  const collapseNode = (nodeId) => {
    if (collapsedNodes.includes(nodeId)) {
      setCollapsedNodes((collapsedNodes) =>
        collapsedNodes.filter((item) => item !== nodeId)
      );
    } else {
      setCollapsedNodes([...collapsedNodes, nodeId]);
    }
  };

  const createChild = async (docParentId, docName, docType) => {
    await restAPI.post("/codelist", {
      docParentId,
      docName,
      docType,
    });

    setShouldUpdateContent(!shouldUpdateContent);
  };

  return (
    <div className="ui container">
      <DirectoryNodeChildren
        children={content}
        collapsedNodes={collapsedNodes}
        collapseNode={collapseNode}
        createChild={createChild}
      />
    </div>
  );
};

export default DirectoryTree;
