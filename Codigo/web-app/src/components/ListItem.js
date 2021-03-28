import "./ListItem.css";
import React, { useEffect, useRef, useState } from "react";
import ContextMenu from "./ContextMenu";
import ListItemContext from "./ListItemContext";
import ListItemCreation from "./ListItemCreation";

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

const ListItem = ({ id, name, lastModified, type, content }) => {
  const [isCollapsed, setCollapsed] = useState(false);
  const [isCreatingNew, setIsCreatingNew] = useState(false);
  const [contextPosition, setContextPosition] = useState({
    left: null,
    top: null,
  });
  const ref = useRef();
  const childrenRef = useRef();
  const contextRef = useRef();

  const itemType =
    type === "FOLDER" ? (isCollapsed ? "folderOpen" : "folder") : "file";
  const { dropIcon, typeIcon } = listItemConfig[itemType];

  useEffect(() => {
    const onBodyClick = (event) => {
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

  const collapseChildren = (event) => {
    if (
      (childrenRef.current && childrenRef.current.contains(event.target)) ||
      (contextRef.current && contextRef.current.contains(event.target))
    ) {
      return;
    }

    setCollapsed(!isCollapsed);
  };

  const onContextMenu = (event) => {
    if (childrenRef.current && childrenRef.current.contains(event.target))
      return;

    event.preventDefault();
    setContextPosition({ left: event.clientX, top: event.clientY });
  };

  const renderedContextMenu = () => {
    if (!contextPosition.left || !contextPosition.top) return null;

    return (
      <ContextMenu xy={contextPosition}>
        <ListItemContext ref={contextRef} createNew={setIsCreatingNew} />
      </ContextMenu>
    );
  };

  const renderedChildren = (content) => {
    if (content && isCollapsed) {
      return content.map((item) => {
        return (
          <div key={item.docId} className="list">
            <ListItem
              id={item.docId}
              name={item.docName}
              content={item.docChildren}
              lastModified={item.docLastmodified}
              type={item.docType}
            />
          </div>
        );
      });
    }

    return null;
  };

  const renderListItemCreation = () => {
    if (isCreatingNew) {
      return (
        <div key={-1} className="list">
          <ListItemCreation parent={id} isCreatingNew={setIsCreatingNew} />
        </div>
      );
    }

    return null;
  };

  return (
    <div
      ref={ref}
      className="list-item item"
      onClick={collapseChildren}
      onContextMenu={onContextMenu}
    >
      {renderedContextMenu()}
      <i className={`${dropIcon} icon`} />
      <i className={`${typeIcon} icon`} />
      <div className="content">
        <div className="content-title">
          <div className="header">{name}</div>
          <div className="description">{`Ultima modificação em ${lastModified}`}</div>
        </div>
        <div>{renderListItemCreation()}</div>
        <div ref={childrenRef}>{renderedChildren(content)}</div>
      </div>
    </div>
  );
};

export default ListItem;
