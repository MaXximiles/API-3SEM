import React from "react";
import ListItem from "./ListItem";

const List = ({ content }) => {
  const renderedContent = content.map((item) => {
    return (
      <ListItem
        key={item.docId}
        name={item.docName}
        lastModified={item.docLastmodified}
        type={item.docType}
        content={item.docChildren}
      />
    );
  });

  return <div className="folder-list ui list">{renderedContent}</div>;
};

export default List;
