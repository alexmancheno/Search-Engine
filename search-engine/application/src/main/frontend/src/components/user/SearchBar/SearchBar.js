import React,  {Component} from 'react';
import style from './style.css';

export default class SearchBar extends Component {
    render() {
        return (
            <div className={style.main} >
                <div className="input-group">
                    <input type="text" className="form-control" placeholder="Get a phat search" />
                    <div className="input-group-append">
                        <button className="btn btn-secondary" type="button">
                            <i className="fa fa-search"></i>
                        </button>
                    </div>
                </div>
            </div>
        )
    }
}