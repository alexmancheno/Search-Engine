import React, { Component } from 'react';

export default class SearchResults extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="list-group">
                {this.props.searchResults.map(result => (
                    <a key={result.toString()} href={result} className="list-group-item list-group-item-action">{result}</a>
                ))}
            </div>
        )
    }
}