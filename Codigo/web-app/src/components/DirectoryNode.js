import "./ListItem.css";
import React, { useEffect, useRef, useState } from "react";
import ContextMenu from "./ContextMenu";
import DirectoryNodeChildren from "./DirectoryNodeChildren";
import DirectoryNodeContext from "./DirectoryNodeContext";
import DirectoryNodeCreation from "./DirectoryNodeCreation";

const listItemConfig = {
  file: {
    dropIcon: null,
    typeIcon: "file alternate",
  },
  folder: {
    dropIcon: "caret right",
    typeIcon: "folder",
  },
  folderOpen: {
    dropIcon: "caret down",
    typeIcon: "folder open",
  },
};

const DirectoryNode = ({ node, collapsedNodes, collapseNode, createChild }) => {
  const [isCreatingNew, setIsCreatingNew] = useState(false);
  const [contextPosition, setContextPosition] = useState({
    left: null,
    top: null,
  });
  const ref = useRef();
  const childrenRef = useRef();
  const contextRef = useRef();

  useEffect(() => {
    const onBodyClick = () => {
      setContextPosition({ left: null, top: null });
    };

    const onBodyContextMenu = (event) => {
      if (
        (ref.current && !ref.current.contains(event.target)) ||
        (childrenRef.current && childrenRef.current.contains(event.target))
      ) {
        setContextPosition({ left: null, top: null });
      }
    };

    document.body.addEventListener("click", onBodyClick);
    document.body.addEventListener("contextmenu", onBodyContextMenu);

    return () => {
      document.body.removeEventListener("click", onBodyClick);
      document.body.removeEventListener("contextmenu", onBodyContextMenu);
    };
  }, []);

  const itemType = () => {
    if (node.docType === "FOLDER") {
      return collapsedNodes.includes(node.docId) ? "folderOpen" : "folder";
    } else {
      return "file";
    }
  };
  const { dropIcon, typeIcon } = listItemConfig[itemType()];

  const collapseChildren = (event) => {
    if (
      (childrenRef.current && childrenRef.current.contains(event.target)) ||
      (contextRef.current && contextRef.current.contains(event.target))
    ) {
      return;
    }

    collapseNode(node.docId);
  };

  const renderChildren = (children) => {
    if (children && collapsedNodes.includes(node.docId)) {
      return (
        <DirectoryNodeChildren
          children={node.docChildren}
          collapsedNodes={collapsedNodes}
          collapseNode={collapseNode}
          createChild={createChild}
        />
      );
    }

    return null;
  };

  const onContextMenu = (event) => {
    if (childrenRef.current && childrenRef.current.contains(event.target))
      return;

    event.preventDefault();
    setContextPosition({ left: event.clientX, top: event.clientY });
  };

  const renderContextMenu = () => {
    if (contextPosition.left || contextPosition.top) {
      return (
        <ContextMenu xy={contextPosition}>
          <DirectoryNodeContext ref={contextRef} createNew={setIsCreatingNew} />
        </ContextMenu>
      );  
    }

    return null;
  };

  const renderListItemCreation = () => {
    if (isCreatingNew) {
      return (
        <div key={-1} className="list">
          <DirectoryNodeCreation
            parent={node.docId}
            isCreatingNew={setIsCreatingNew}
            createChild={createChild}
          />
        </div>
      );
    }

    return null;
  };

  // const renderNodeTitle = () => {
  //   if (isUpdating) {
  //     return (
  //       <div className="content-title">
  //         <div className="ui transparent input">
  //           <input
  //             ref={inputRef}
  //             type="text"
  //             placeholder="Novo diretório"
  //             value={title}
  //             onChange={(e) => setTitle(e.target.value)}
  //             onKeyPress={(e) =>
  //               e.key === "Enter" ? createNew(parent, title, "FOLDER") : null
  //             }
  //             onBlur={() => createNew(parent, title, "FOLDER")}
  //           />
  //         </div>
  //         <div className="description">{`Criando novo diretório`}</div>
  //       </div>
  //     );
  //   } else {
  //     return (
  //       <div className="content-title">
  //         <div className="header">{node.docName}</div>
  //         <div className="description">{`Ultima modificação em ${node.docLastmodified}`}</div>
  //       </div>
  //     );
  //   }
  // };

  return (
    <div
      ref={ref}
      className="list-item item"
      onClick={collapseChildren}
      onContextMenu={onContextMenu}
    >
      {renderContextMenu()}
      <i className={`${dropIcon} icon`} />
      <i className={`${typeIcon} icon`} />
      <div className="content">
        <div className="content-title">
          <div className="header">{node.docName}</div>
          <div className="description">{`Ultima modificação em ${node.docLastmodified}`}</div>
        </div>
        <div ref={childrenRef}>
          {renderListItemCreation()}
          {renderChildren(node.docChildren)}
        </div>
      </div>
    </div>
  );
};

export default DirectoryNode;
